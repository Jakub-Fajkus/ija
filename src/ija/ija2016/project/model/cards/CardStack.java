package ija.ija2016.project.model.cards;

public class CardStack extends CardDeck implements CardStackInterface {

    public CardStack(int size) {
        super(size);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;

        CardStack cardStack = (CardStack) o;

        return this.cards != null ? this.cards.equals(cardStack.cards) : cardStack.cards == null;
    }

    @Override
    public int hashCode() {
        return this.cards != null ? this.cards.hashCode() : 0;
    }


    /**
     * Metoda odebere kartu z vrcholu zasobniku a vrati ji. Vraci null, pokud na zasobniku nic neni.
     *
     * @return ija.ija2016.project.game.cards.CardInterface nebo null, pokud je zasobnik prazdny
     */
    @Override
    public CardInterface pop() {
        if (this.cards.isEmpty()) {
            return null;
        }

        return this.cards.pop();
    }
}