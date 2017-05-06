package ija.ija2016.project.game;

import ija.ija2016.project.game.command.MoveCommandInterface;
import ija.ija2016.project.game.command.MoveGameCommand;
import ija.ija2016.project.game.persistence.LoadStateException;
import ija.ija2016.project.game.persistence.PersistStateException;
import ija.ija2016.project.game.persistence.filesystem.FilesystemFactory;
import ija.ija2016.project.game.ui.AIFactory;
import ija.ija2016.project.model.board.AbstractFactorySolitaire;
import ija.ija2016.project.model.cards.CardDeckInterface;
import ija.ija2016.project.model.cards.CardInterface;
import ija.ija2016.project.model.cards.CardStackInterface;

import java.util.ArrayList;
import java.util.Stack;

public class Game implements GameInterface {
    private CardDeckInterface[] targetPacks;
    private CardDeckInterface drawingDeck;
    private CardDeckInterface wastingDeck;
    private CardStackInterface[] workingCardStacks;
    private transient Stack<MoveCommandInterface> history;
    private transient ArrayList<GameObserverInterface> observers;

    public Game(AbstractFactorySolitaire factorySolitaire) {
        this.observers = new ArrayList<>();

        CardDeckInterface cardDeck = factorySolitaire.createShuffledCardDeck();

        CardDeckInterface[] targetPacks = new CardDeckInterface[factorySolitaire.getCountOfTargetDecks()];
        for (CardInterface.Color color : CardInterface.Color.values()) {
            targetPacks[this.getTargetPackIndexForColor(color)] = factorySolitaire.createTargetPack(color);
        }

        CardStackInterface[] workingCardStacks = new CardStackInterface[factorySolitaire.getCountOfWorkingStacks()];
        for (int i = 0; i < factorySolitaire.getCountOfWorkingStacks(); i++) {
            workingCardStacks[i] = factorySolitaire.createWorkingPack();

            for (int j = 0; j <= i; j++) {
                CardInterface card = cardDeck.pop();
                if (!workingCardStacks[i].put(card)) {
                    System.out.println("Could not add a card " + card);
                }
            }

            if (workingCardStacks[i].get() != null) {
                workingCardStacks[i].get().turnFaceUp();
            }
        }

        this.init(targetPacks, cardDeck, factorySolitaire.createWastingDeck(), workingCardStacks, new Stack<>());
    }

    /**
     * Initialize the inner state of the game with the given objects.
     *
     * @param targetPacks
     * @param drawingDeck
     * @param wastingDeck
     * @param workingCardStacks
     */
    public void init(CardDeckInterface[] targetPacks, CardDeckInterface drawingDeck, CardDeckInterface wastingDeck, CardStackInterface[] workingCardStacks, Stack<MoveCommandInterface> history) {
        this.targetPacks = targetPacks;
        this.drawingDeck = drawingDeck;
        this.wastingDeck = wastingDeck;
        this.workingCardStacks = workingCardStacks;
        //if we have out history, so do not lose it
        if (this.history != null && history == null) {
        } else {
            this.history = history;
        }
    }

    /**
     * Initialize the inner state of the game from the given game.
     *
     * @param game Game object which data should be copied into the current game
     */
    public void init(GameInterface game) {
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
    public boolean move(CardDeckInterface source, CardDeckInterface destination, int count) {
        MoveGameCommand command = new MoveGameCommand(source, destination, count);

        return this.move(command);
    }

    /**
     * Move all cards from source to destination.
     * <p>
     * This should perform a safe try to change the game's state.
     * If the resulting state would not be valid, it should not allow to get to the state.
     * This call must maintain the consistency of the game(not fully performing the move).
     * <p>
     * This call creates a history point which can be reverted by the undo operation.
     *
     * @param source      Source card deck
     * @param destination Destination card deck
     * @return True on success, false otherwise
     */
    @Override
    public boolean move(CardDeckInterface source, CardDeckInterface destination) {
        MoveGameCommand command = new MoveGameCommand(source, destination, 0);

        return this.move(command);
    }

    /**
     * Same as {@link #move(CardDeckInterface source, CardDeckInterface destination, int count)}
     *
     * @param command MoveCommandInterface instance
     */
    @Override
    public boolean move(MoveCommandInterface command) {
        //todo: create a defensive copy of the command? :D

        //perform the command
        //on success, return true
        if (command.execute(this)) {
            //add the command into a history
            history.add(command);

            this.notifyObservers();
            return true;
        } else {
            //on failure, revert the state
            try {
                this.init(command.undo());

                return true;
//                return this.undo() != null;

//                return false;
            } catch (UndoException exception) {
                //error occurred, cannot undo.. the world is over
                exception.printStackTrace();

                return false;
            } finally {
                this.notifyObservers();
            }
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
    public MoveCommandInterface undo() throws UndoException {
        System.out.println("GAME: undoing");
        if (this.history.empty()) {
            return null;
        }

        MoveCommandInterface command = this.history.pop();
        try {
            this.init(command.undo());
        } catch (UndoException exception) {
            //error occurred, cannot undo.. the world is over
            exception.printStackTrace();
            return null;
        } finally {
            this.notifyObservers();
        }


        return command;
    }

    /**
     * Go back from the undo.
     * <p>
     * Move the current game state more to the present.
     * <p>
     * This should return the game state to state before the last undo was done.
     * This method can be called many times, each time reverting the last undo.
     *
     * @throws RedoException When the redo cannot be done.
     */
    @Override
    public MoveCommandInterface redo() throws RedoException {
        //delete? :D
        return null;
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
            try {
                this.undo();
            } catch (UndoException e) {
                e.printStackTrace();

                return false;
            }
        }

        return true;
    }

    /**
     * Get hints for the next move.
     *
     * @throws TipException When there is no move to be performed. There is no way of finishing the game.
     */
    @Override
    public ArrayList<MoveCommandInterface> tip() throws TipException {

        return (new AIFactory()).getAI().getPossibleMoves(this);
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
        this.notifyObservers();
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
        return false;
    }

    @Override
    public CardDeckInterface[] getTargetPacks() {
        return targetPacks;
    }

    @Override
    public CardDeckInterface getDrawingDeck() {
        return drawingDeck;
    }

    @Override
    public CardDeckInterface getWastingDeck() {
        return wastingDeck;
    }

    @Override
    public CardStackInterface[] getWorkingCardStacks() {
        return workingCardStacks;
    }

    @Override
    public Stack<MoveCommandInterface> getHistory() {
        return history;
    }


    public ArrayList<CardInterface> getAllCards() {
        ArrayList<CardInterface> cards = new ArrayList<>();

        for (CardDeckInterface stack : this.workingCardStacks) {
            cards.addAll(stack.getAll());
        }

        for (CardDeckInterface stack : this.targetPacks) {
            cards.addAll(stack.getAll());
        }

        cards.addAll(this.getDrawingDeck().getAll());
        cards.addAll(this.getWastingDeck().getAll());

        return cards;
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
     * Get index to an array of target packs. Color.ordinal should be enough.
     *
     * @param color
     * @return
     */
    private int getTargetPackIndexForColor(CardInterface.Color color) {
        return color.ordinal();
    }
}
