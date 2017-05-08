/*
 * IJA2016 Solitaire
 * Jakub Fajkus & David Czernin
 */

package ija.ija2016.project.model.cards;

/**
 * Card stack used as a target stack.
 */
public class TargetCardStack extends CardStack implements TargetCardStackInterface {
    private Card.Color deckColor;

    public TargetCardStack(int size, Card.Color deckColor) {
        super(size);
        this.deckColor = deckColor;
    }

    /**
     * Put the cards at the stack.
     * <p>
     * The card must have the same color as the current stack.
     * If the stack is empty, the only card accepted is a card with value of 1.
     * If there is a card in the stack, the inserted card's value must be exactly one higher than the card in the stack.
     */
    @Override
    public boolean put(CardInterface card) {
        if (card.getColor() != this.deckColor) {
            return false;
        }

        //only the ace(value = 1) can be put in the empty stack
        if (this.isEmpty() && card.getValue() != 1) {
            return false;
        }

        if (!this.isEmpty() && this.cards.peek().getValue() != card.getValue() - 1) {
            return false;
        }

        return super.put(card);
    }

    @Override
    public CardInterface.Color getColorOfDeck() {
        return this.deckColor;
    }
}
