package ija.ija2016.project.game.persistence.bytearray;

public class ByteArrayFactory {
    public ByteArrayStateLoaderInterface getLoader() {
        return new ByteArrayStateLoader();
    }

    public ByteArrayStateSaverInterface getSaver() {
        return new ByteArrayStateSaver();
    }
}
