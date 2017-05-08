/*
 * IJA2016 Solitaire
 * Jakub Fajkus & David Czernin
 */

package ija.ija2016.project.model.cards;

/**
 * Represents a target card stack.
 * <p>
 * The player's aim is to put all cards of the same color into the stack.
 */
public interface TargetCardStackInterface extends CardStackInterface {
    /**
     * Get the color of the stack.
     *
     * @return Color of the cards that will be accepted by the stack
     */
    CardInterface.Color getColorOfDeck();
}
