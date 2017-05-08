/*
 * IJA2016 Solitaire
 * Jakub Fajkus & David Czernin
 */

package ija.ija2016.project.model.cards;

import java.util.ArrayList;

/**
 * Represents a single card stack.
 */
public interface CardStackInterface {
    /**
     * Removes the card from the top of the stack. The removed card is then returned.
     *
     * @return The card from the top. If the stack is empty, returns null
     */
    CardInterface pop();

    /**
     * Put the card to the top of the stack.
     *
     * @param card CardInterface Card to be put.
     * @return Result of the operation - true on success, false otherwise
     */
    boolean put(CardInterface card);

    /**
     * <p>
     * Put all cards from the array to the stack.
     * The item on index 0 will be inserted first, thus it will be in the bottom of the stack.
     *
     * @param cards Cards to put to the deck
     * @return true on success, false otherwise
     */
    boolean put(CardInterface[] cards);

    /**
     * Add card to the stack and skip all semantic checks.
     *
     * @param card Card to be put.
     * @return True if the card was inserted, false otherwise
     */
    boolean forcePut(CardInterface card);

    /**
     * @return Count of cards in the stack
     */
    int size();

    /**
     * Get the card on the top of the stack(the card is not removed and stays in the stack).
     *
     * @return The card from the top. If the stack is empty, returns null.
     */
    CardInterface get();

    /**
     * Get a card at the given index. The bottom card is at the index 0, the top is at the index size()-1.
     * If the stack is empty or the index is out of bounds, returns null
     *
     * @param index Index of the card
     * @return The card at the index or null if the index is invalid
     */
    CardInterface get(int index);

    /**
     * Get all cards. Do not remove them from the stack.
     *
     * @return List of all cards
     */
    ArrayList<CardInterface> getAll();

    /**
     * Remove all cards from the stack
     */
    void removeAll();


    /**
     * Test whether the stack is empty
     *
     * @return True if the stack is empty, false otherwise
     */
    boolean isEmpty();

    /**
     * Get the number of cards facing up.
     *
     * @return Returns 0 If the deck is empty
     */
    int getNumberOfCardsFacingUp();
}
