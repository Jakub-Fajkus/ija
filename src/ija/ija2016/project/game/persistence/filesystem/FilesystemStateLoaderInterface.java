package ija.ija2016.project.game.persistence.filesystem;

import ija.ija2016.project.game.GameInterface;
import ija.ija2016.project.game.persistence.LoadStateException;

public interface FilesystemStateLoaderInterface {
    String FILE_EXTENSION = ".solitaire";

    /**
     * Load the game state from the object with the given name.
     *
     * @param name Object name. This can be a file, database or network storage.
     * @throws LoadStateException When the state could not be loaded.
     */
    GameInterface loadState(String name) throws LoadStateException;
}
