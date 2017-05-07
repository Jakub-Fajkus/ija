package ija.ija2016.project.game;

import ija.ija2016.project.model.cards.CardDeckInterface;

public class GameRuleValidator {
    private GameInterface game;

    public GameRuleValidator(GameInterface game) {
        this.game = game;
    }

    public boolean validate(CardDeckInterface source, CardDeckInterface destination, int count) {
        // one from drawing to wasting
        if (source == this.game.getDrawingDeck() && destination == this.game.getWastingDeck() && count == 1) {
            return true;
        }

        // all from wasting to drawing
        if (source == this.game.getWastingDeck() && destination == this.game.getDrawingDeck() && count == 0) {
            return true;
        }

        // one from wasting to working
        if (source == this.game.getWastingDeck() && this.isWorkingDeck(destination) && count == 1) {
            return true;
        }

        // one from wasting to target
        if (source == this.game.getWastingDeck() && this.isTargetStack(destination) && count == 1) {
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
        for (CardDeckInterface working : this.game.getWorkingCardStacks()) {
            if (working == deck) {
                return true;
            }
        }

        return false;
    }

    public boolean isTargetStack(CardDeckInterface deck) {
        for (CardDeckInterface target : this.game.getTargetPacks()) {
            if (target == deck) {
                return true;
            }
        }

        return false;
    }
}
