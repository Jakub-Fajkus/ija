package ija.ija2016.project.game.cards;

/**
 * Created by Jakub on 25.03.17.
 */
public class TargetCardDeck extends CardDeck {
    private Card.Color deckColor;

    public TargetCardDeck(int size, Card.Color deckColor) {
        super(size);
        this.deckColor = deckColor;
    }

    @Override
    public boolean put(ija.ija2016.project.model.cards.Card card) {
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

}
