/*
 * IJA2016 Solitaire
 * Jakub Fajkus & David Czernin
 */

package ija.ija2016.project.game.command;

import java.io.Serializable;

public interface GameCommandInterface extends Serializable {
    boolean execute();
}
