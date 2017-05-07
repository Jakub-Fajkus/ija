package ija.ija2016.project.model.cards;

public class TargetCardDeck extends CardDeck implements TargetCardDeckInterface {
    private Card.Color deckColor;

    public TargetCardDeck(int size, Card.Color deckColor) {
        super(size);
        this.deckColor = deckColor;
    }

    @Override
    public boolean put(CardInterface card) {
        if (card.color() != this.deckColor) {
            return false;
        }

        if (this.isEmpty() && card.value() != 1) { //na prazdny balicek lze vlozit pouze eso
            return false;
        }

        if (!this.isEmpty() && this.cards.peek().value() != card.value() - 1) {
            return false;
        }

        return super.put(card);
    }

    /**
     * Get the color of the deck.
     *
     * @return
     */
    @Override
    public CardInterface.Color getColorOfDeck() {
        return this.deckColor;
    }
}
