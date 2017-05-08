/*
 * IJA2016 Solitaire
 * Jakub Fajkus & David Czernin
 */

package ija.ija2016.project.game;

import ija.ija2016.project.game.command.MoveCommandInterface;
import ija.ija2016.project.model.cards.AbstractFactorySolitaire;

public class GameWithMoveRestrictions extends Game {
    private transient GameRuleValidator validator;

    public GameWithMoveRestrictions(AbstractFactorySolitaire factorySolitaire) {
        super(factorySolitaire);

        this.validator = new GameRuleValidator(this);
    }

    @Override
    public boolean move(MoveCommandInterface command) {
        if (this.validator.validate(command.getSource(), command.getDestination(), command.getCount())) {
            return super.move(command);
        }
        return false;
    }

    /**
     * Initialize the inner state of the game from the given game.
     *
     * @param game Game object which data should be copied into the current game
     */
    @Override
    public void init(GameInterface game) {
        this.validator = new GameRuleValidator(this);
        super.init(game);
    }
}
