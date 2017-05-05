package ija.ija2016.project.game.command;

import ija.ija2016.project.game.GameInterface;
import ija.ija2016.project.game.UndoException;
import ija.ija2016.project.game.persistence.LoadStateException;
import ija.ija2016.project.game.persistence.PersistStateException;
import ija.ija2016.project.game.persistence.bytearray.ByteArrayFactory;
import ija.ija2016.project.game.persistence.bytearray.ByteArrayStateLoaderInterface;
import ija.ija2016.project.game.persistence.bytearray.ByteArrayStateSaverInterface;
import ija.ija2016.project.model.board.FactoryKlondike;
import ija.ija2016.project.model.cards.CardDeckInterface;
import ija.ija2016.project.model.cards.CardStackInterface;

public class MoveGameCommand extends GameCommand implements MoveCommandInterface {
    private CardDeckInterface source;
    private CardDeckInterface destination;
    private int count;
    private transient byte[] gameData;

    public MoveGameCommand(CardDeckInterface source, CardDeckInterface destination, int count) {
        this.source = source;
        this.destination = destination;
        this.count = count;
    }

    @Override
    public boolean execute(GameInterface game) {
        ByteArrayStateSaverInterface saver = (new ByteArrayFactory()).getSaver();

        try {
            this.gameData = saver.persistState(game);
        } catch (PersistStateException e) {
            e.printStackTrace();
            return false;
        }

        if (count == 0) {
            while (!source.isEmpty()) {
                if (!destination.put(source.pop())) {
                    return false;
                }
            }
        } else {
            if (count > source.size()) {
                System.out.println("Can not take more that the source has");
                return false;
            }

            CardStackInterface newStack = (new FactoryKlondike()).createEmptyCardStack();

            for (int i = 0; i < count; i++) {
                if (!newStack.put(source.pop())) {
                    return false;
                }
            }

            for (int i = count; i > 0; i--) {
                if (!destination.put(newStack.pop())) {
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    public GameInterface undo() throws UndoException {
        //return the game to the previous state
        ByteArrayFactory factory = new ByteArrayFactory();
        ByteArrayStateLoaderInterface loader = factory.getLoader();

        GameInterface newGame;

        try {
            newGame = loader.loadState(this.gameData);
        } catch (LoadStateException e) {
            e.printStackTrace();
            throw new UndoException();
        }

        return newGame;

    }

    /**
     * Get the source of the move
     *
     * @return CardDeckInterface Card deck which is the source.
     */
    @Override
    public CardDeckInterface getSource() {
        return this.source;
    }

    /**
     * Get the destination of the move
     *
     * @return CardDeckInterface Card deck which is the destination.
     */
    @Override
    public CardDeckInterface getDestination() {
        return this.destination;
    }

    /**
     * Set destination of the move
     *
     * @param destination
     */
    @Override
    public void setDestination(CardDeckInterface destination) {
        this.destination = destination;
    }

    /**
     * Get a count of objects to be moved.
     * <p>
     * If the count is equal to zero, all objects will be moved
     *
     * @return int Count of the objects
     */
    @Override
    public int getCount() {
        return this.count;
    }

    /**
     * Set the game data used to redo the command.
     * <p>
     * The data should represent the game BEFORE the command was executed.
     *
     * @param data Byte array containg the game state
     */
    @Override
    public void setGameData(byte[] data) {
        this.gameData = data;
    }
}
