package ija.ija2016.project.game;

import ija.ija2016.project.model.board.FactoryKlondike;
import ija.ija2016.project.model.cards.CardDeckInterface;

public class GameFactory {
    public GameInterface createGame() {
        return new GameWithMoveRestrictions(new FactoryKlondike());
    }

    public GameInterface createGameWithoutMoveRestrictions() {
        return new Game(new FactoryKlondike());
    }

    public CardDeckInterface getNewCardDeck() {
        return new FactoryKlondike().createShuffledCardDeck();
    }
}
