/*
 * IJA2016 Solitaire
 * Jakub Fajkus & David Czernin
 */

package ija.ija2016.project.game.command;

import ija.ija2016.project.model.cards.CardStackInterface;

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
     * @return CardStackInterface Card deck which is the source.
     */
    CardStackInterface getSource();

    /**
     * Set source of the move
     *
     * @param source
     */
    void setSource(CardStackInterface source);

    /**
     * Get the destination of the move
     *
     * @return CardStackInterface Card deck which is the destination.
     */
    CardStackInterface getDestination();

    /**
     * Set destination of the move
     *
     * @param destination
     */
    void setDestination(CardStackInterface destination);

    /**
     * Get a count of objects to be moved.
     * <p>
     * If the count is equal to zero, all objects will be moved
     *
     * @return int Count of the objects
     */
    int getCount();

    /**
     * Set the count of cards to move
     *
     * @param count
     */
    void setCount(int count);
}
