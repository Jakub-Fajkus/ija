package ija.ija2016.project;

import ija.ija2016.project.game.Game;
import ija.ija2016.project.game.GameInterface;
import ija.ija2016.project.game.persistence.LoadStateException;
import ija.ija2016.project.game.persistence.PersistStateException;
import ija.ija2016.project.game.persistence.bytearray.ByteArrayStateLoader;
import ija.ija2016.project.game.persistence.bytearray.ByteArrayStateLoaderInterface;
import ija.ija2016.project.game.persistence.bytearray.ByteArrayStateSaver;
import ija.ija2016.project.game.persistence.bytearray.ByteArrayStateSaverInterface;
import ija.ija2016.project.model.board.FactoryKlondike;
import ija.ija2016.project.model.cards.CardStack;

public class Main {
    public static void main(String[] args) {
        FactoryKlondike factoryKlondike = new FactoryKlondike();
        Game game = new Game(factoryKlondike);
        CardStack s = new CardStack(13);
        s.put(game.drawingDeck.get());
        game.move(game.drawingDeck, game.wastingDeck, s);

        s = new CardStack(13);
        s.put(game.drawingDeck.get());
        game.move(game.drawingDeck, game.wastingDeck, s);

        ByteArrayStateSaverInterface stateSaver = new ByteArrayStateSaver();
        byte[] data;

        try {
            data = stateSaver.persistState(game);
        } catch (PersistStateException e) {
            e.printStackTrace();
            return;
        }

        GameInterface g = null;
        ByteArrayStateLoaderInterface stateLoader = new ByteArrayStateLoader();
        try {
            g = stateLoader.loadState(data);
        } catch (LoadStateException e) {
            e.printStackTrace();
        }

        System.out.println(game == g);
    }

}
