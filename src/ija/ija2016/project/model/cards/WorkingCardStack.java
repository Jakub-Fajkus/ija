/*
 * IJA2016 Solitaire
 * Jakub Fajkus & David Czernin
 */

package ija.ija2016.project.model.cards;

/**
 * Card stack used as a working stack.
 */
public class WorkingCardStack extends CardStack {

    public WorkingCardStack(int size) {
        super(size);
    }

    /**
     * If the stack is empty and the given card is facing up, it must be a cars with value of 13.
     * The cards' colors in the stack must alternate(can only put the black card on the red and vice versa)
     * The given card's value must be one lower than the card at the top of the stack.
     * Turns the given card face up.
     */
    @Override
    public boolean put(CardInterface card) {
        if (this.isEmpty()) {
            if (card.isTurnedFaceUp()) {
                //kral
                return card.getValue() == 13 && super.put(card);
            }

            return super.put(card);
        }

        if (this.cards.peek().similarColorTo(card)) {
            return false;
        }

        if (this.cards.peek().getValue() != (card.getValue() + 1)) {
            return false;
        }

        if (super.put(card)) {
            card.turnFaceUp();

            return true;
        }

        return false;
    }

    /**
     * Pop a card from the stack.
     * After the card is popped, turns the card at the top facing up.
     */
    @Override
    public CardInterface pop() {
        CardInterface popped = super.pop();

        if (!this.isEmpty()) {
            super.get().turnFaceUp();
        }

        return popped;
    }
}
