package ija.ija2016.project.game.ui;

import ija.ija2016.project.game.GameInterface;
import ija.ija2016.project.game.command.MoveCommandInterface;

import java.util.ArrayList;

public interface AIStrategyInterface {
    /**
     * Get all possible moves for the game
     *
     * @return all possible moves for the game
     */
    ArrayList<MoveCommandInterface> getPossibleMoves(GameInterface game);
}
