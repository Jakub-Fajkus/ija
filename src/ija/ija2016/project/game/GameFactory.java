package ija.ija2016.project.game;

import ija.ija2016.project.model.board.FactoryKlondike;

public class GameFactory {
    public GameInterface createGame() {
        return new GameWithMoveRestrictions(new FactoryKlondike());
    }

    public GameInterface createGameWithoutMoveRestrictions() {
        return new Game(new FactoryKlondike());
    }
}
