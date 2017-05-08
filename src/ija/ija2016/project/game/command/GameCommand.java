/*
 * IJA2016 Solitaire
 * Jakub Fajkus & David Czernin
 */

package ija.ija2016.project.game.command;

public abstract class GameCommand implements GameCommandInterface {
    public abstract boolean execute();
}
