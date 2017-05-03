package ija.ija2016.project.game.persistence.filesystem;

public class FilesystemFactory {
    public FilesystemStateLoaderInterface getLoader() {
        return new FilesystemStateLoader();
    }

    public FilesystemStateSaverInterface getSaver() {
        return new FilesystemStateSaver();
    }
}
