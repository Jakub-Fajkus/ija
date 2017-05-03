package ija.ija2016.project.game;

import ija.ija2016.project.game.command.MoveCommandInterface;
import ija.ija2016.project.model.board.AbstractFactorySolitaire;

public class GameWithMoveRestrictions extends Game {
    protected transient GameRuleValidator validator;

    public GameWithMoveRestrictions(AbstractFactorySolitaire factorySolitaire) {
        super(factorySolitaire);

        this.validator = new GameRuleValidator(this);
    }

    @Override
    public boolean move(MoveCommandInterface command) {
        return this.validator.validate(command.getSource(), command.getDestination(), command.getCount()) && super.move(command);
    }
}
