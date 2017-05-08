package ija.ija2016.project.model.cards;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Basic implementation of CardStackInterface.
 */
public class CardStack implements CardStackInterface {

    Stack<CardInterface> cards;
    private int maxDeckSize;

    public CardStack(int size) {
        this.maxDeckSize = size;

        this.cards = new Stack<>();
        this.cards.ensureCapacity(size);
    }

    @Override
    public boolean put(CardInterface card) {
        if (this.cards.size() == this.maxDeckSize) {
            return false;
        }

        this.cards.push(card);

        return true;
    }

    @Override
    public boolean put(CardInterface[] cards) {
        for (CardInterface card : cards) {
            if (!this.put(card)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public int size() {
        return this.cards.size();
    }

    @Override
    public CardInterface get() {
        if (this.cards.empty()) {
            return null;
        } else {
            return this.cards.peek();
        }
    }

    @Override
    public CardInterface get(int index) {

        if (this.cards.empty()) {
            return null;
        }

        try {
            return this.cards.get(index);
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }

    }

    @Override
    public ArrayList<CardInterface> getAll() {
        ArrayList<CardInterface> returnCards = new ArrayList<>();

        returnCards.addAll(this.cards);

        return returnCards;
    }

    @Override
    public void removeAll() {
        this.cards.clear();
    }

    @Override
    public boolean isEmpty() {
        return this.cards.empty();
    }

    @Override
    public int getNumberOfCardsFacingUp() {
        int count = 0;

        for (CardInterface card : this.cards) {
            if (card.isTurnedFaceUp()) {
                count++;
            }
        }

        return count;
    }

    @Override
    final public boolean forcePut(CardInterface card) {
        if (this.cards.size() == this.maxDeckSize) {
            return false;
        }

        this.cards.push(card);

        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;

        CardStack cardStack = (CardStack) o;

        return this.cards != null ? this.cards.equals(cardStack.cards) : cardStack.cards == null;
    }

    @Override
    public int hashCode() {
        return this.cards != null ? this.cards.hashCode() : 0;
    }


    @Override
    public CardInterface pop() {
        if (this.cards.isEmpty()) {
            return null;
        }

        return this.cards.pop();
    }
}