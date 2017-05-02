package ija.ija2016.project.game.command;

import ija.ija2016.project.game.UndoException;

import java.io.Serializable;

public interface GameCommandInterface extends Serializable {
    boolean execute();

    void undo() throws UndoException;
}
