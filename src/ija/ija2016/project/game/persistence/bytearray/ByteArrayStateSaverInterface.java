package ija.ija2016.project.game.persistence.bytearray;

import ija.ija2016.project.game.GameInterface;
import ija.ija2016.project.game.persistence.PersistStateException;

public interface ByteArrayStateSaverInterface {
    /**
     * Save the current game state to a byte array
     *
     * @param game Game object which will be saved
     * @throws PersistStateException When the state could not be saved.
     */
    byte[] persistState(GameInterface game) throws PersistStateException;
}
