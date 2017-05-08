/*
 * IJA2016 Solitaire
 * Jakub Fajkus & David Czernin
 */

package ija.ija2016.project.game;

import ija.ija2016.project.game.command.MoveCommandInterface;
import ija.ija2016.project.game.persistence.LoadStateException;
import ija.ija2016.project.game.persistence.PersistStateException;
import ija.ija2016.project.model.cards.AbstractFactorySolitaire;
import ija.ija2016.project.model.cards.CardInterface;
import ija.ija2016.project.model.cards.CardStackInterface;
import ija.ija2016.project.model.cards.TargetCardStackInterface;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Stack;

public interface GameInterface extends Serializable {

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
    boolean move(CardStackInterface source, CardStackInterface destination, int count);

    /**
     * Add a deck of cards to the game. The cards will be redistributed to appropriate stacks.
     * <p>
     * All observers will be notified about the change
     */
    void initializeWithCards(CardStackInterface deck);


    /**
     * Same as {@link #move(CardStackInterface source, CardStackInterface destination, int count)}
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
     */
    void undo();

    /**
     * Get the game to the starting state.
     * <p>
     * This should restart the game state to state before the player started to play
     *
     * @return If the restart was successful, false otherwise
     */
    boolean restartGame();

    /**
     * Get hints for the next move.
     */
    ArrayList<MoveCommandInterface> tip();


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
     * Check if the game is already finished - all cards are on the target stacks
     * <p>
     * This method will count all cards in the target stacks and compare it to the overall card count
     *
     * @return
     */
    boolean isFinished();

    /**
     * Initialize the inner state of the game with the given objects.
     *
     * @param targetPacks
     * @param drawingDeck
     * @param wastingDeck
     * @param workingCardStacks
     */
    void init(TargetCardStackInterface[] targetPacks, CardStackInterface drawingDeck, CardStackInterface wastingDeck, CardStackInterface[] workingCardStacks, Stack<GameHistory> history);

    TargetCardStackInterface[] getTargetPacks();

    CardStackInterface getDrawingDeck();

    CardStackInterface getWastingDeck();

    CardStackInterface[] getWorkingCardStacks();

    Stack<GameHistory> getHistory();

    GameState getState();

    /**
     * Get all cards in the game
     *
     * @return
     */
    ArrayList<CardInterface> getAllCards();

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
    void addObserver(GameObserverInterface observer);

    /**
     * Get the solitaire factory.
     *
     * @return
     */
    AbstractFactorySolitaire getSolitaireFactory();
}

