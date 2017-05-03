package ija.ija2016.project;

import ija.ija2016.project.game.GameFactory;
import ija.ija2016.project.game.GameInterface;
import ija.ija2016.project.game.persistence.LoadStateException;
import ija.ija2016.project.game.persistence.PersistStateException;
import ija.ija2016.project.model.board.AbstractFactorySolitaire;
import ija.ija2016.project.model.board.FactoryKlondike;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PersistenceTest {

    protected AbstractFactorySolitaire factory;
    protected GameInterface game;

    @Before
    public void setUp() {
        this.factory = new FactoryKlondike();
        this.game = (new GameFactory()).createGameWithoutMoveRestrictions();
    }

    @Test
    public void testSaveAndLoadGame() {
        game.move(game.getDrawingDeck(), game.getWastingDeck(), 10);
        Assert.assertEquals("The drawing deck contains 14 cards before persist", 14, game.getDrawingDeck().size());
        Assert.assertEquals("The wasting deck contains 10 cards before persist", 10, game.getWastingDeck().size());

        try {
            game.persistState("/Users/Jakub/IdeaProjects/ija/testSaveGame");
        } catch (PersistStateException e) {
            e.printStackTrace();
            System.out.println("neslo to");
        }

        game.move(game.getDrawingDeck(), game.getWastingDeck(), 10);

        try {
            game.loadState("/Users/Jakub/IdeaProjects/ija/testSaveGame");
        } catch (LoadStateException e) {
            e.printStackTrace();
            System.out.println("neslo to");
        }

        Assert.assertEquals("The drawing deck contains 14 cards after persist", 14, game.getDrawingDeck().size());
        Assert.assertEquals("The wasting deck contains 10 cards after persist", 10, game.getWastingDeck().size());
    }
}
