package ija.ija2016.project.game.command;

import ija.ija2016.project.model.cards.CardDeckInterface;

/**
 * Represents a single card deck movement.
 * <p>
 * The movement has 3 actors:
 * - object - what is moving
 * - source - from which place is the object moving
 * - destination - to which place should the object be placed
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
     * Get a object to be moved
     *
     * @return CardDeckInterface Card deck which is the moving object.
     */
    CardDeckInterface getObject();

    /**
     * Set the game data used to redo the command.
     * <p>
     * The data should represent the game BEFORE the command was executed.
     *
     * @param data Byte array containg the game state
     */
    void setGameData(byte[] data);
}
