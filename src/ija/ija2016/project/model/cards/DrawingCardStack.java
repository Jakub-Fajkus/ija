package ija.ija2016.project.model.cards;

/**
 * Card stack used as a drawing stack.
 * The player takes cards from the drawing stack and adds the to the wasting stack.
 */
public class DrawingCardStack extends CardStack {
    public DrawingCardStack(int size) {
        super(size);
    }

    /**
     * Turns the card facing down if the parent's {@link CardStack#put(CardInterface)} was successful
     */
    @Override
    public boolean put(CardInterface card) {
        if (super.put(card)) {
            card.turnFaceDown();

            return true;
        }
        return false;
    }

    /**
     * Calls parent's {@link CardStack#put(CardInterface)} for all cards.
     * If the parent's call is successful, turns all cards face down.
     */
    @Override
    public boolean put(CardInterface[] cards) {
        if (super.put(cards)) {
            for (CardInterface card : cards) {
                card.turnFaceDown();
            }

            return true;
        }

        return false;
    }
}
