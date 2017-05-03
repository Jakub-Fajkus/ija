package ija.ija2016.project.game.command;

import ija.ija2016.project.model.cards.CardDeckInterface;

/**
 * Represents a single card deck movement.
 * <p>
 * The movement has 2 actors and one parameter:
 * - source - from which place is the object moving
 * - destination - to which place should the object be placed
 * - count - how many cards are moving
 */
public interface MoveCommandInterface extends GameCommandInterface {
    /**
     * Get the source of the move
     *
     * @return CardDeckInterface Card deck which is the source.
     */
    CardDeckInterface getSource();

    /**
     * Get the destination of the move
     *
     * @return CardDeckInterface Card deck which is the destination.
     */
    CardDeckInterface getDestination();

    /**
     * Get a count of objects to be moved.
     *
     * If the count is equal to zero, all objects will be moved
     *
     * @return int Count of the objects
     */
    int getCount();

    /**
     * Set the game data used to redo the command.
     *
     * The data should represent the game BEFORE the command was executed.
     *
     * @param data Byte array containg the game state
     */
    void setGameData(byte[] data);
}
