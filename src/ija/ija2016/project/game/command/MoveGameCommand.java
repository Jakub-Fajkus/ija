package ija.ija2016.project.game.command;

import ija.ija2016.project.game.Game;
import ija.ija2016.project.game.GameInterface;
import ija.ija2016.project.game.UndoException;
import ija.ija2016.project.game.persistence.LoadStateException;
import ija.ija2016.project.game.persistence.bytearray.ByteArrayFactory;
import ija.ija2016.project.game.persistence.bytearray.ByteArrayStateLoaderInterface;
import ija.ija2016.project.model.cards.CardDeckInterface;

public class MoveGameCommand extends GameCommand implements MoveCommandInterface {
    private CardDeckInterface source;
    private CardDeckInterface destination;
    private CardDeckInterface object;
    private Game game;
    private transient byte[] gameData;

    public MoveGameCommand(CardDeckInterface source, CardDeckInterface destination, CardDeckInterface object, Game game) {
        this.source = source;
        this.destination = destination;
        this.object = object;
        this.game = game;
    }


    @Override
    public boolean execute() {
        //do stuff with the game
        return true;
    }

    @Override
    public void undo() throws UndoException {
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

        this.game.init(newGame);

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
     * Get a object to be moved
     *
     * @return CardDeckInterface Card deck which is the moving object.
     */
    @Override
    public CardDeckInterface getObject() {
        return this.object;
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
