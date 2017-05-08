package ija.ija2016.project.game.command;

import ija.ija2016.project.model.cards.CardStackInterface;
import ija.ija2016.project.model.cards.FactoryKlondike;

public class MoveGameCommand extends GameCommand implements MoveCommandInterface {
    private CardStackInterface source;
    private CardStackInterface destination;
    private int count;

    public MoveGameCommand(CardStackInterface source, CardStackInterface destination, int count) {
        this.source = source;
        this.destination = destination;
        this.count = count;
    }

    @Override
    public boolean execute() {
        if (this.count == 0) {
            while (!this.source.isEmpty()) {
                if (!this.destination.put(this.source.pop())) {
                    return false;
                }
            }
        } else {
            if (this.count > this.source.size()) {
                System.out.println("Can not take more that the source has");
                return false;
            }

            CardStackInterface newStack = (new FactoryKlondike()).createEmptyCardStack();

            for (int i = 0; i < this.count; i++) {
                if (!newStack.put(this.source.pop())) {
                    return false;
                }
            }

            for (int i = this.count; i > 0; i--) {
                if (!this.destination.put(newStack.pop())) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Get the source of the move
     *
     * @return CardStackInterface Card deck which is the source.
     */
    @Override
    public CardStackInterface getSource() {
        return this.source;
    }

    /**
     * Set source of the move
     *
     * @param source
     */
    @Override
    public void setSource(CardStackInterface source) {
        this.source = source;
    }

    /**
     * Get the destination of the move
     *
     * @return CardStackInterface Card deck which is the destination.
     */
    @Override
    public CardStackInterface getDestination() {
        return this.destination;
    }

    /**
     * Set destination of the move
     *
     * @param destination
     */
    @Override
    public void setDestination(CardStackInterface destination) {
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
     * Set the count of cards to move
     *
     * @param count
     */
    @Override
    public void setCount(int count) {
        this.count = count;
    }
}
