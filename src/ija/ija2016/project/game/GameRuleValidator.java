package ija.ija2016.project.game;

import ija.ija2016.project.model.cards.CardDeckInterface;

public class GameRuleValidator {
    private GameInterface game;

    public GameRuleValidator(GameInterface game) {
        this.game = game;
    }

    public boolean validate(CardDeckInterface source, CardDeckInterface destination, int count) {
        // one from drawing to wasting
        if (source == game.getDrawingDeck() && destination == game.getWastingDeck() && count == 1) {
            return true;
        }

        // all from wasting to drawing
        if (source == game.getWastingDeck() && destination == game.getDrawingDeck() && count == 0) {
            return true;
        }

        // one from wasting to working
        if (source == game.getWastingDeck() && this.isWorkingDeck(destination) && count == 1) {
            return true;
        }

        // one from wasting to target
        if (source == game.getWastingDeck() && this.isTargetStack(destination) && count == 1) {
            return true;
        }

        // one from target to working
        if (this.isTargetStack(source) && this.isWorkingDeck(destination) && count == 1) {
            return true;
        }

        // one from working to target
        if (this.isWorkingDeck(source) && this.isTargetStack(destination) && count == 1) {
            return true;
        }

        // one from working to working
        return this.isWorkingDeck(source) && this.isWorkingDeck(destination);

    }

    private boolean isWorkingDeck(CardDeckInterface deck) {
        for (CardDeckInterface working : game.getWorkingCardStacks()) {
            if (working == deck) {
                return true;
            }
        }

        return false;
    }

    private boolean isTargetStack(CardDeckInterface deck) {
        for (CardDeckInterface target : game.getTargetPacks()) {
            if (target == deck) {
                return true;
            }
        }

        return false;
    }
}
