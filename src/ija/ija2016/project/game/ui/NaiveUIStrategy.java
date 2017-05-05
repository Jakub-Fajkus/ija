package ija.ija2016.project.game.ui;

import ija.ija2016.project.game.GameInterface;
import ija.ija2016.project.game.command.GameCommandInterface;

public class NaiveUIStrategy implements UIStrategyInterface {
    /**
     * Get all possible moves for the game
     *
     * @param game
     * @return
     */
    @Override
    public GameCommandInterface[] getPossibleMoves(GameInterface game) {
        //check if any card can be moved to the target stacks
        //check if the card from the wasting deck could be moved to the target
        //check if any card can be moved in the working stacks


        return new GameCommandInterface[0];
    }
}
