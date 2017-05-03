package ija.ija2016.project.model.cards;

public class WastingDeck extends CardDeck {
    public WastingDeck(int size) {
        super(size);
    }

    @Override
    public boolean put(CardInterface card) {
        if (super.put(card)) {
            card.turnFaceUp();

            return true;
        }

        return false;
    }

    /**
     * Vlozi vsechny karty na zasobnik v poradi, v jakem jsou v poli - 0. index bude na dne zasobniku
     *
     * @param cards
     * @return true on success, false otherwise
     */
    @Override
    public boolean put(CardInterface[] cards) {
        if (super.put(cards)) {
            for (CardInterface card : cards) {
                card.turnFaceUp();
            }

            return true;
        }

        return false;
    }
}
