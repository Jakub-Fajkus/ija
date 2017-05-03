package ija.ija2016.project;

import ija.ija2016.project.game.Game;
import ija.ija2016.project.model.board.AbstractFactorySolitaire;
import ija.ija2016.project.model.board.FactoryKlondike;
import ija.ija2016.project.model.cards.CardInterface;
import ija.ija2016.project.model.cards.CardStackInterface;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GameMoveTest {

    protected AbstractFactorySolitaire factory;
    protected Game game;

    @Before
    public void setUp() {
        this.factory = new FactoryKlondike();
        this.game = new Game(this.factory);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testMoveFromDrawingToWorking() {
        Assert.assertTrue("Can move from drawing to wasting", game.move(game.getDrawingDeck(), game.getWastingDeck(), 1));
        CardStackInterface[] workingStacks = game.getWorkingCardStacks();

        for (int i = 0; i < 7; i++) {
            Assert.assertTrue("The topmost card should be facing up", workingStacks[i].get().isTurnedFaceUp());
        }
        Assert.assertTrue("Should be able to put a card to the working stack", game.move(game.getDrawingDeck(), workingStacks[1], 1));
        Assert.assertTrue("The new card should be facing up after it is inserted into the working stack", game.getWastingDeck().get().isTurnedFaceUp());
        Assert.assertTrue("The card under the new card should remain facing up", workingStacks[1].get(workingStacks[1].size() - 2).isTurnedFaceUp());

        CardStackInterface poppedStack = workingStacks[2].pop(workingStacks[2].get(workingStacks[2].size() - 2));
        CardInterface card1 = poppedStack.pop();
        workingStacks[2].put(card1);

        Assert.assertEquals("The 1 index card is 9(S)U", "9(S)U", workingStacks[2].get().toString());
        Assert.assertTrue("Can move one card from working to working", this.game.move(workingStacks[2], workingStacks[1], 1));
    }

    @Test
    public void testMoveFromDrawingToWasting() {
        Assert.assertEquals("Drawing deck has 24 cards", 24, game.getDrawingDeck().size());
        for (int i = 0; i < 24; i++) {
            Assert.assertFalse("Cards in the drawing deck should be facing down", game.getDrawingDeck().get(i).isTurnedFaceUp());
        }

        Assert.assertEquals("The wasting pack should be empty", 0, game.getWastingDeck().size());
        Assert.assertTrue("Can draw one card from drawing to wasting", game.move(game.getDrawingDeck(), game.getWastingDeck(), 1));
        Assert.assertEquals("The wasting pack should contain one card", 1, game.getWastingDeck().size());
        Assert.assertEquals("The drawing pack should contain one card less", 23, game.getDrawingDeck().size());

        Assert.assertTrue("Can draw two cards from drawing to wasting", game.move(game.getDrawingDeck(), game.getWastingDeck(), 2));
        Assert.assertEquals("The wasting pack should contain three card", 3, game.getWastingDeck().size());
        Assert.assertEquals("The drawing pack should contain three card less", 21, game.getDrawingDeck().size());
    }

    @Test
    public void testMoveFromWastingToTarget() {
        Assert.assertTrue("Can 13 cards from drawing to wasting", this.game.move(game.getDrawingDeck(), game.getWastingDeck(), 11));
        Assert.assertEquals("The ace(A(D)U) is in the wasting", "A(D)U", game.getWastingDeck().get().toString());


        Assert.assertTrue("Can move the ace(A(D)U) from wasting to the target pack", game.move(game.getWastingDeck(), game.getTargetPacks()[1], 1));
        Assert.assertEquals("The ace(A(D)U) is in the target", "A(D)U", game.getTargetPacks()[1].get().toString());
    }
}