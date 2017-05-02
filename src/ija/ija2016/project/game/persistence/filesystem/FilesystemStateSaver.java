package ija.ija2016.project.game.persistence.filesystem;

import ija.ija2016.project.game.GameInterface;
import ija.ija2016.project.game.persistence.PersistStateException;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class FilesystemStateSaver implements FilesystemStateSaverInterface {

    /**
     * Save the current game state to a object with the given name
     *
     * @param game Game object which will be saved
     * @param name Object name. This can be a file, database or network storage.
     * @throws PersistStateException When the state could not be saved.
     */
    @Override
    public void persistState(GameInterface game, String name) throws PersistStateException {
        try {
            FileOutputStream fileOut = new FileOutputStream(name);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(game);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
            throw new PersistStateException();
        }
    }
}
