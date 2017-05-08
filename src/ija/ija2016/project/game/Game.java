/*
 * IJA2016 Solitaire
 * Jakub Fajkus & David Czernin
 */

package ija.ija2016.project.game;

import ija.ija2016.project.game.ai.AIFactory;
import ija.ija2016.project.game.command.MoveCommandInterface;
import ija.ija2016.project.game.command.MoveGameCommand;
import ija.ija2016.project.game.persistence.LoadStateException;
import ija.ija2016.project.game.persistence.PersistStateException;
import ija.ija2016.project.game.persistence.filesystem.FilesystemFactory;
import ija.ija2016.project.model.cards.AbstractFactorySolitaire;
import ija.ija2016.project.model.cards.CardInterface;
import ija.ija2016.project.model.cards.CardStackInterface;
import ija.ija2016.project.model.cards.TargetCardStackInterface;

import java.util.ArrayList;
import java.util.Stack;

public class Game implements GameInterface {
    private GameState state;

    private transient Stack<GameHistory> history;
    private transient ArrayList<GameObserverInterface> observers;
    private transient AbstractFactorySolitaire factorySolitaire;

    public Game(AbstractFactorySolitaire factorySolitaire) {
        this.observers = new ArrayList<>();
        this.factorySolitaire = factorySolitaire;

        CardStackInterface cardDeck = factorySolitaire.createShuffledCardStack();
        this.initializeWithCards(cardDeck);
    }

    /**
     * Initialize the inner state of the game with the given objects.
     *
     * @param targetPacks
     * @param drawingDeck
     * @param wastingDeck
     * @param workingCardStacks
     */
    public void init(TargetCardStackInterface[] targetPacks, CardStackInterface drawingDeck, CardStackInterface wastingDeck, CardStackInterface[] workingCardStacks, Stack<GameHistory> history) {

        this.state = new GameState(targetPacks, drawingDeck, wastingDeck, workingCardStacks, this.factorySolitaire);

        //if we have our history, do not lose it
        if (this.history == null || history != null) {
            this.history = history;
        }

        this.notifyObservers();
    }

    /**
     * Initialize the inner state of the game from the given game.
     *
     * @param game Game object which data should be copied into the current game
     */
    void init(GameInterface game) {
        this.init(game.getTargetPacks(), game.getDrawingDeck(), game.getWastingDeck(), game.getWorkingCardStacks(), game.getHistory());
    }

    /**
     * Move count cards from source to destination.
     * <p>
     * This should perform a safe try to change the game's state.
     * If the resulting state would not be valid, it should not allow to get to the state.
     * This call must maintain the consistency of the game(not fully performing the move).
     * <p>
     * This call creates a history point which can be reverted by the undo operation.
     *
     * @param source      Source card deck
     * @param destination Destination card deck
     * @param count       Count of cards to be moved. If the count is zero, all cards will be moved.
     * @return True on success, false otherwise
     */
    @Override
    public boolean move(CardStackInterface source, CardStackInterface destination, int count) {
        MoveGameCommand command = new MoveGameCommand(source, destination, count);

        return this.move(command);
    }

    /**
     * Add a deck of cards to the game. The cards will be redistributed to appropriate stacks.
     *
     * @param deck
     */
    @Override
    public void initializeWithCards(CardStackInterface deck) {
        TargetCardStackInterface[] targetPacks = new TargetCardStackInterface[this.factorySolitaire.getCountOfTargetStacks()];
        for (CardInterface.Color color : CardInterface.Color.values()) {
            targetPacks[this.getTargetPackIndexForColor(color)] = this.factorySolitaire.createTargetStack(color);
        }

        CardStackInterface[] workingCardStacks = new CardStackInterface[this.factorySolitaire.getCountOfWorkingStacks()];
        for (int i = 0; i < this.factorySolitaire.getCountOfWorkingStacks(); i++) {
            workingCardStacks[i] = this.factorySolitaire.createWorkingStack();

            for (int j = 0; j <= i; j++) {
                CardInterface card = deck.pop();
                if (!workingCardStacks[i].forcePut(card)) {
                    System.out.println("Could not add a card " + card);
                }
            }

            if (workingCardStacks[i].get() != null) {
                workingCardStacks[i].get().turnFaceUp();
            }
        }

        this.init(targetPacks, deck, this.factorySolitaire.createWastingStack(), workingCardStacks, new Stack<>());
    }

    /**
     * Same as {@link #move(CardStackInterface source, CardStackInterface destination, int count)}
     *
     * @param command MoveCommandInterface instance
     */
    @Override
    public boolean move(MoveCommandInterface command) {
        GameHistory newSnapshot = new GameHistory(command, this.state, this);

        //save the current game state
        newSnapshot.saveGameState();

        //perform the command
        //on success, return true
        if (newSnapshot.executeCommand()) {
            //add the command into a history
            this.history.add(newSnapshot);

            this.notifyObservers();
            return true;
        } else {
            //on failure, revert the state
            newSnapshot.undoCommand();

            this.notifyObservers();
            return false;
        }
    }

    private void notifyObservers() {
        for (GameObserverInterface observer : this.observers) {
            observer.updateOnGameChange();
        }
    }

    /**
     * Undo the last move.
     * <p>
     * Move the current game state more to the past.
     * <p>
     * This should revert the game state to state before the last move was done.
     * This method can be called many times, each time reverting a single move.
     *
     * @throws UndoException When the undo cannot be done.
     */
    @Override
    public void undo() {
        System.out.println("GAME: undoing");

        if (!this.history.empty()) {
            GameHistory lastSnapshot = this.history.pop();
            lastSnapshot.undoCommand();
            this.notifyObservers();
        }
    }

    /**
     * Get the game to the starting state.
     * <p>
     * This should restart the game state to state before the player started to play
     *
     * @return If the restart was successful, false otherwise
     */
    @Override
    public boolean restartGame() {
        while (!this.history.empty()) {
            this.undo();
        }

        return true;
    }

    /**
     * Get hints for the next move.
     *
     * @throws TipException When there is no move to be performed. There is no way of finishing the game.
     */
    @Override
    public ArrayList<MoveCommandInterface> tip() {
        //save the current state as the strategy may change the game state
        GameHistory newSnapshot = new GameHistory(null, this.state, this);

        //save the current game state
        newSnapshot.saveGameState();
        this.history.add(newSnapshot);

        ArrayList<MoveCommandInterface> tips = (new AIFactory()).getAI().getPossibleMoves(this);

        this.undo();

        return tips;
    }

    /**
     * Save the current game state to a file with the given path
     *
     * @param path Path to the file. The file will be created or overwritten
     * @throws PersistStateException When the state could not be saved.
     */
    @Override
    public void persistState(String path) throws PersistStateException {
        FilesystemFactory factory = new FilesystemFactory();
        factory.getSaver().persistState(this, path);
    }

    /**
     * Load the game state from the file.
     *
     * @param path Path to the file. The file must exist and have the appropriate format.
     * @throws LoadStateException When the state could not be loaded.
     */
    @Override
    public void loadState(String path) throws LoadStateException {
        FilesystemFactory factory = new FilesystemFactory();
        this.init(factory.getLoader().loadState(path));
    }

    /**
     * Check if the game is already finished - all cards are on the target stacks
     * <p>
     * This method will count all cards in the target stacks and compare it to the overall card count
     *
     * @return
     */
    @Override
    public boolean isFinished() {
        int countOfCardsInTheTargets = 0;

        for (CardStackInterface deck : this.state.getTargetPacks()) {
            countOfCardsInTheTargets += deck.size();
        }

        return countOfCardsInTheTargets == this.getAllCards().size();
    }

    @Override
    public TargetCardStackInterface[] getTargetPacks() {
        return this.state.getTargetPacks();
    }

    @Override
    public CardStackInterface getDrawingDeck() {
        return this.state.getDrawingDeck();
    }

    @Override
    public CardStackInterface getWastingDeck() {
        return this.state.getWastingDeck();
    }

    @Override
    public CardStackInterface[] getWorkingCardStacks() {
        return this.state.getWorkingCardStacks();
    }

    @Override
    public Stack<GameHistory> getHistory() {
        return this.history;
    }

    public GameState getState() {
        return this.state;
    }

    /**
     * Get all cards in the game
     *
     * @return
     */
    @Override
    public ArrayList<CardInterface> getAllCards() {
        return this.state.getAllCards();
    }

    /**
     * Add a observer which will be called for each change of the game.
     * <p>
     * The events notified:
     * - the success of the move() call
     * - the success of the undo() call
     * - the success of the loadState() call
     *
     * @param observer
     */
    @Override
    public void addObserver(GameObserverInterface observer) {
        if (observer != null) {
            this.observers.add(observer);
        }
    }

    /**
     * Get the solitaire factory.
     *
     * @return
     */
    @Override
    public AbstractFactorySolitaire getSolitaireFactory() {
        return this.factorySolitaire;
    }

    /**
     * Get index to an array of target packs. Color.ordinal should be enough.
     *
     * @param color
     * @return
     */
    private int getTargetPackIndexForColor(CardInterface.Color color) {
        return color.ordinal();
    }
}
