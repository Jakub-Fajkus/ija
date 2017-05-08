package ija.ija2016.project.game;

import ija.ija2016.project.model.cards.CardStackInterface;
import ija.ija2016.project.model.cards.FactoryKlondike;

public class GameFactory {
    public GameInterface createGame() {
        return new GameWithMoveRestrictions(new FactoryKlondike());
    }

    public CardStackInterface getNewCardDeck() {
        return new FactoryKlondike().createShuffledCardStack();
    }
}
