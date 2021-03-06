package ija.ija2016.project.game.persistence.filesystem;

import ija.ija2016.project.game.Game;
import ija.ija2016.project.game.GameInterface;
import ija.ija2016.project.game.persistence.LoadStateException;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class FilesystemStateLoader implements FilesystemStateLoaderInterface {
    /**
     * Load the game state from the object with the given name.
     *
     * @param name Object name. This can be a file, database or network storage.
     * @throws LoadStateException When the state could not be loaded.
     */
    @Override
    public GameInterface loadState(String name) throws LoadStateException {
        Game game;

        try {
            FileInputStream fileIn = new FileInputStream(name);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            game = (Game) in.readObject();
            in.close();
            fileIn.close();

            return game;
        } catch (IOException | ClassNotFoundException i) {
            i.printStackTrace();
            throw new LoadStateException();
        }
    }
}
