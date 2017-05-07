package ija.ija2016.project.game.persistence.filesystem;

import ija.ija2016.project.game.GameInterface;
import ija.ija2016.project.game.persistence.PersistStateException;

public interface FilesystemStateSaverInterface {
    String FILE_EXTENSION = ".solitaire";

    /**
     * Save the current game state to a object with the given name
     *
     * @param game Game object which will be saved
     * @param name Object name. This can be a file, database or network storage.
     * @throws PersistStateException When the state could not be saved.
     */
    void persistState(GameInterface game, String name) throws PersistStateException;
}
