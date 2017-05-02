package ija.ija2016.project.game.persistence.bytearray;

import ija.ija2016.project.game.Game;
import ija.ija2016.project.game.GameInterface;
import ija.ija2016.project.game.persistence.LoadStateException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class ByteArrayStateLoader implements ByteArrayStateLoaderInterface {
    /**
     * Load the game state from the object with the given name.
     *
     * @throws LoadStateException When the state could not be loaded.
     */
    @Override
    public GameInterface loadState(byte[] data) throws LoadStateException {
        Game game;

        if (data == null) {
            throw new LoadStateException();
        }

        try {
            ByteArrayInputStream arrIn = new ByteArrayInputStream(data);
            ObjectInputStream in = new ObjectInputStream(arrIn);

            game = (Game) in.readObject();
            in.close();

            return game;
        } catch (IOException i) {
            i.printStackTrace();
            throw new LoadStateException();
        } catch (ClassNotFoundException c) {
            throw new LoadStateException();
        }
    }
}
