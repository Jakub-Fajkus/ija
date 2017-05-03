package ija.ija2016.project.game.persistence.bytearray;

import ija.ija2016.project.game.GameInterface;
import ija.ija2016.project.game.persistence.LoadStateException;

public interface ByteArrayStateLoaderInterface {
    /**
     * Load state of the game from the given byte array
     *
     * @param data Data containing the game
     * @return GameInterface instance
     * @throws LoadStateException On error
     */
    GameInterface loadState(byte[] data) throws LoadStateException;
}
