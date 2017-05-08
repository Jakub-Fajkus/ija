package ija.ija2016.project.model.cards;

import java.io.Serializable;

/**
 * Represents a single card in the game.
 */
public interface CardInterface extends Serializable {
    /**
     * Get the card's color.
     *
     * @return Color
     */
    Color getColor();

    /**
     * Get the card's value.
     *
     * @return int
     */
    int getValue();

    /**
     * Test if the card is facing up(you can see the card's value).
     *
     * @return True if the card is facing up, false otherwise
     */
    boolean isTurnedFaceUp();

    /**
     * Turn the card face up. If the card is already facing up, do nothing.
     *
     * @return True if the card was facing down and was turned, false otherwise
     */
    boolean turnFaceUp();

    /**
     * Turn the card face down. If the card is already facing down, do nothing.
     *
     * @return True if the card was facing up and was turned, false otherwise
     */
    boolean turnFaceDown();

    /**
     * Test whether the given card has the similar color as the actual card.
     * <p>
     * Example: Clubs and Spades are similar colors, as well as Diamonds and Hearts
     *
     * @param c Card to test
     * @return True whether the colors are similar, false otherwise
     */
    boolean similarColorTo(CardInterface c);

    /**
     * Get the card's value as the string.
     *
     * Values 2-10 are represented as string. All other values are transformed to appropriate letter.
     * Example: 1 = A, 11 = J, 12 = Q, 13 = K
     *
     * @return The value as string
     */
    String getValueAsString();

    /**
     * Color of the card.
     */
    enum Color {
        SPADES('S'), DIAMONDS('D'), HEARTS('H'), CLUBS('C');

        private final char character;

        Color(char character) {
            this.character = character;
        }

        /**
         * Test whether the given color is similar to the actual color
         * <p>
         * Example: Clubs and Spades are similar colors, as well as Diamonds and Heards
         */
        public boolean similarColorTo(CardInterface.Color c) {
            return (c.isBlack() && this.isBlack()) || (!c.isBlack() && !this.isBlack());
        }

        /**
         * Test whether the color is black
         * <p>
         * Spades and Clubs are considered as black.
         *
         * @return
         */
        public boolean isBlack() {
            return this.character == SPADES.character || this.character == CLUBS.character;
        }


        @Override
        public String toString() {
            return String.valueOf(this.character);
        }
    }
}
