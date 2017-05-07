package ija.ija2016.project.game.ai;

import ija.ija2016.project.game.GameInterface;
import ija.ija2016.project.game.command.GameCommandInterface;

public interface UIStrategyInterface {
    /**
     * Get all possible moves for the game
     *
     * @return
     */
    GameCommandInterface[] getPossibleMoves(GameInterface game);
}
