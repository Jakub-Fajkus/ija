package ija.ija2016.project.game;

import ija.ija2016.project.model.cards.CardDeckInterface;
import ija.ija2016.project.model.cards.FactoryKlondike;

public class GameFactory {
    public GameInterface createGame() {
        return new GameWithMoveRestrictions(new FactoryKlondike());
    }

    public CardDeckInterface getNewCardDeck() {
        return new FactoryKlondike().createShuffledCardDeck();
    }
}
