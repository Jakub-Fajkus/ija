package ija.ija2016.project.game;

import ija.ija2016.project.game.command.MoveCommandInterface;
import ija.ija2016.project.game.command.MoveGameCommand;
import ija.ija2016.project.game.persistence.LoadStateException;
import ija.ija2016.project.game.persistence.PersistStateException;
import ija.ija2016.project.game.persistence.filesystem.FilesystemFactory;
import ija.ija2016.project.model.board.AbstractFactorySolitaire;
import ija.ija2016.project.model.cards.CardDeckInterface;
import ija.ija2016.project.model.cards.CardInterface;
import ija.ija2016.project.model.cards.CardStackInterface;

import java.util.Stack;

/*
todo: add observer(or so) to notify the GUI about the change of the game state.
when the state is unserialized, the old CardDeck objects are discarded and the new ones must be passed to the GUI,
which should rerender all the objects
the game object itself is not changed at all while doing the undo
 */

public class Game implements GameInterface {

    private CardDeckInterface[] targetPacks;
    private CardDeckInterface drawingDeck;
    private CardDeckInterface wastingDeck;
    private CardStackInterface[] workingCardStacks;
    private Stack<MoveCommandInterface> history;

    public Game(AbstractFactorySolitaire factorySolitaire) {
        CardDeckInterface cardDeck = factorySolitaire.createUnshuffledCardDeck();
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
        this.history = history;
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
        MoveGameCommand command = new MoveGameCommand(source, destination, count, this);

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
        MoveGameCommand command = new MoveGameCommand(source, destination, 0, this);

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

        //add the command into a history
        history.add(command);

        //perform the command
        //on success, return true
        if (command.execute()) {
            return true;
        } else {
            //on failure, revert the state
            try {
                command.undo();
            } catch (UndoException exception) {
                //error occurred, cannot undo.. the world is over
                exception.printStackTrace();
                return false;
            }
        }

        return true;
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
        MoveCommandInterface command = this.history.pop();
        try {
            command.undo();
        } catch (UndoException exception) {
            //error occurred, cannot undo.. the world is over
            exception.printStackTrace();
            return null;
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
        return null;
    }

    /**
     * Get hints for the next move.
     *
     * @throws TipException When there is no move to be performed. There is no way of finishing the game.
     */
    @Override
    public MoveCommandInterface[] tip() throws TipException {
        return new MoveCommandInterface[0];
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
