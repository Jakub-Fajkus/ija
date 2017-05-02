package ija.ija2016.project.game.persistence.bytearray;

import ija.ija2016.project.game.GameInterface;
import ija.ija2016.project.game.persistence.PersistStateException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class ByteArrayStateSaver implements ByteArrayStateSaverInterface {
    /**
     * Save the current game state to a object with the given name
     *
     * @param game Game object which will be saved
     * @throws PersistStateException When the state could not be saved.
     */
    @Override
    public byte[] persistState(GameInterface game) throws PersistStateException {
        try {
            ByteArrayOutputStream arrOut = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(arrOut);

            out.writeObject(game);
            out.close();
            return arrOut.toByteArray();
        } catch (IOException i) {
            i.printStackTrace();
        }

        return null;
    }
}
