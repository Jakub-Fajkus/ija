package ija.ija2016.project.model.cards;

public class WorkingCardStack extends CardStack {

    public WorkingCardStack(int size) {
        super(size);
    }

    @Override
    public boolean put(CardInterface card) {
        if (this.isEmpty()) {
            if (card.isTurnedFaceUp()) {
                //kral
                return card.value() == 13 && super.put(card);
            }

            return super.put(card);
        }

        //pokud posledni karta neni obrazkem nahoru, tak nekontrolujeme pravidla(probiha inicializace)
        if (!this.cards.peek().isTurnedFaceUp()) {
            return super.put(card);

        }

        //zajistit stridani barev
        if (this.cards.peek().color().similarColorTo(card.color())) {
            return false;
        }

        if (this.cards.peek().value() != (card.value() + 1)) {
            return false;
        }

        if (super.put(card)) {
            card.turnFaceUp();

            return true;
        }

        return false;
    }

    /**
     * Metoda odebere kartu z vrcholu zasobniku a vrati ji. Vraci null, pokud na zasobniku nic neni.
     *
     * @return ija.ija2016.project.game.cards.CardInterface nebo null, pokud je zasobnik prazdny
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
