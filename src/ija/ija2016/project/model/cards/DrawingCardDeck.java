package ija.ija2016.project.model.cards;

public class DrawingCardDeck extends CardDeck {
    public DrawingCardDeck(int size) {
        super(size);
    }

    @Override
    public boolean put(CardInterface card) {
        if (super.put(card)) {
            card.turnFaceDown();

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
                card.turnFaceDown();
            }

            return true;
        }

        return false;
    }
}
