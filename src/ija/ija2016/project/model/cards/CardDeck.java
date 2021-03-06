package ija.ija2016.project.model.cards;

import java.util.EmptyStackException;
import java.util.Stack;

public class CardDeck implements CardDeckInterface {
    protected Stack<CardInterface> cards;
    protected int maxDeckSize;

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
        if (this.cards.size() == maxDeckSize) {
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
     * Test, zda je balíček karet prázdný.
     *
     * @return Vrací true, pokud je balíček prázdný.
     */
    @Override
    public boolean isEmpty() {
        return this.cards.empty();
    }
}
