package ija.ija2016.project.game;

import ija.ija2016.project.game.command.MoveCommandInterface;
import ija.ija2016.project.game.persistence.LoadStateException;
import ija.ija2016.project.game.persistence.PersistStateException;
import ija.ija2016.project.model.cards.CardDeckInterface;
import ija.ija2016.project.model.cards.CardStackInterface;

import java.io.Serializable;
import java.util.Stack;

public interface GameInterface extends Serializable {

    /**
     * Move count cards from source to destination.
     *
     * This should perform a safe try to change the game's state.
     * If the resulting state would not be valid, it should not allow to get to the state.
     * This call must maintain the consistency of the game(not fully performing the move).
     *
     * This call creates a history point which can be reverted by the undo operation.
     *
     * @param source      Source card deck
     * @param destination Destination card deck
     * @param count       Count of cards to be moved. If the count is zero, all cards will be moved.
     * @return True on success, false otherwise
     */
    boolean move(CardDeckInterface source, CardDeckInterface destination, int count);

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
    boolean move(CardDeckInterface source, CardDeckInterface destination);

    /**
     * Same as {@link #move(CardDeckInterface source, CardDeckInterface destination, int count)}
     *
     * @param command MoveCommandInterface instance
     */
    boolean move(MoveCommandInterface command);

    /**
     * Undo the last move.
     * <p>
     * Move the current game state more to the past.
     * <p>
     * This should return the game state to state before the last move was done.
     * This method can be called many times, each time reverting a single move.
     *
     * @throws UndoException When the undo cannot be done.
     */
    MoveCommandInterface undo() throws UndoException;

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
    MoveCommandInterface redo() throws RedoException;

    /**
     * Get hints for the next move.
     *
     * @throws TipException When there is no move to be performed. There is no way of finishing the game.
     */
    MoveCommandInterface[] tip() throws TipException;


    /**
     * Save the current game state to a file with the given path
     *
     * @param path Path to the file. The file will be created or overwritten
     * @throws PersistStateException When the state could not be saved.
     */
    void persistState(String path) throws PersistStateException;

    /**
     * Load the game state from the file.
     *
     * @param path Path to the file. The file must exist and have the appropriate format.
     * @throws LoadStateException When the state could not be loaded.
     */
    void loadState(String path) throws LoadStateException;

    /**
     * Initialize the inner state of the game with the given objects.
     *
     * @param targetPacks
     * @param drawingDeck
     * @param wastingDeck
     * @param workingCardStacks
     */
    void init(CardDeckInterface[] targetPacks, CardDeckInterface drawingDeck, CardDeckInterface wastingDeck, CardStackInterface[] workingCardStacks, Stack<MoveCommandInterface> history);

    CardDeckInterface[] getTargetPacks();

    CardDeckInterface getDrawingDeck();

    CardDeckInterface getWastingDeck();

    CardStackInterface[] getWorkingCardStacks();

    Stack<MoveCommandInterface> getHistory();
}

