package ija.ija2016.project.model.cards;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Stack;

public class CardDeck implements CardDeckInterface {
    Stack<CardInterface> cards;
    private int maxDeckSize;

    public CardDeck(int size) {
        this.maxDeckSize = size;

        this.cards = new Stack<>();
        this.cards.ensureCapacity(size);
    }

    @Override
    public CardInterface pop() {
        try {
            return this.cards.pop();
        } catch (EmptyStackException e) {
            return null;
        }
    }

    public boolean put(CardInterface card) {
        if (this.cards.size() == this.maxDeckSize) {
            return false;
        }

        this.cards.push(card);

        return true;
    }

    /**
     * Vlozi vsechny karty na zasobnik v poradi, v jakem jsou v poli - 0. index bude na dne zasobniku
     *
     * @param cards
     * @return true on success, false otherwise
     */
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

    /**
     * Vrátí kartu z vrcholu zásobníku (karta zůstává na zásobníku). Pokud je balíček prázdný, vrací null.
     *
     * @return Karta z vrcholu balíčku.
     */
    @Override
    public CardInterface get() {
        try {
            return this.cards.peek();
        } catch (EmptyStackException e) {
            return null;
        }
    }

    /**
     * Vrátí kartu na uvedenem indexu. Spodni karta je na indexu 0, vrchol je na indexu size()-1.
     * Pokud je balíček prázdný, nebo index mimo rozsah, vrací null.
     *
     * @param index int  Pozice karty v balicku.
     * @return Karta z vrcholu balíčku.
     */
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

    /**
     * Get all cards. Do not remove them from the object.
     *
     * @return
     */
    @Override
    public ArrayList<CardInterface> getAll() {
        ArrayList<CardInterface> returnCards = new ArrayList<>();

        returnCards.addAll(this.cards);

        return returnCards;
    }

    /**
     * Remove all cards from the deck
     */
    @Override
    public void removeAll() {
        this.cards.clear();
    }

    /**
     * Test, zda je balíček karet prázdný.
     *
     * @return Vrací true, pokud je balíček prázdný.
     */
    @Override
    public boolean isEmpty() {
        return this.cards.empty();
    }

    /**
     * Get the number of cards facing up.
     *
     * @return Returns 0 If the deck is empty
     */
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

    /**
     * Add card to the deck and skip all semantic checks
     *
     * @param card
     * @return
     */
    @Override
    final public boolean forcePut(CardInterface card) {
        if (this.cards.size() == this.maxDeckSize) {
            return false;
        }

        this.cards.push(card);

        return true;
    }


}
