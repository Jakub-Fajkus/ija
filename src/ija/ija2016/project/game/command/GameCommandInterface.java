package ija.ija2016.project.game.command;

import ija.ija2016.project.game.GameInterface;
import ija.ija2016.project.game.UndoException;

import java.io.Serializable;

public interface GameCommandInterface extends Serializable {
    boolean execute(GameInterface game);

    GameInterface undo() throws UndoException;
}
