package ija.ija2016.project.game.command;

import ija.ija2016.project.game.GameInterface;
import ija.ija2016.project.game.UndoException;

public abstract class GameCommand implements GameCommandInterface {
    public abstract boolean execute(GameInterface game);

    public abstract void undo() throws UndoException;
}
