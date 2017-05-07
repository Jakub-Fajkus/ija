package ija.ija2016.project.game;

import ija.ija2016.project.game.command.MoveCommandInterface;

/**
 * This class represents a snapshot of the game state.
 */
public class GameHistory {
    private MoveCommandInterface command;

    /**
     * The stare represents the game state before the command was executed
     */
    private GameState state;
    private GameInterface game;


    public GameHistory(MoveCommandInterface command, GameState state, GameInterface game) {
        this.command = command;
        this.state = state;
        this.game = game;
    }

    /**
     * Change the game's state to the state of the history
     * <p>
     * This equals to the undo operation
     */
    public void undoCommand() {
        this.game.getState().initFrom(this.state);
    }

    /**
     * Load current game state to the history
     * <p>
     * This is meant to be used while backing up the game state without performing any move
     */
    public void saveGameState() {
        this.state = new GameState(this.game.getState());
    }

    /**
     * Execute the command. If the command fails, the rollback is not performed
     *
     * @return
     */
    public boolean executeCommand() {
        return this.command.execute(this.game);
//        todo:
    }

}
