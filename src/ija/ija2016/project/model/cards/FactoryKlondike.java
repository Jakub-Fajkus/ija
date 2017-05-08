/*
 * IJA2016 Solitaire
 * Jakub Fajkus & David Czernin
 */

package ija.ija2016.project.model.cards;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Factory creating a deck of 52 cards of 4 colors without jokers.
 * <p>
 * See <a href="https://en.wikipedia.org/wiki/Standard_52-card_deck">https://en.wikipedia.org/wiki/Standard_52-card_deck</a>
 */
public class FactoryKlondike extends AbstractFactorySolitaire {

    @Override
    public int getCountOfCardsOfSameColor() {
        return 13;
    }

    @Override
    public int getMaximumNumberOfCardsFacingDownInWorkingStack() {
        return 6;
    }

    @Override
    public int getMaximumNumberOfCardsInWorkingStack() {
        return this.getCountOfCardsOfSameColor() + this.getMaximumNumberOfCardsFacingDownInWorkingStack();
    }

    @Override
    public int getCountOfWorkingStacks() {
        return 7;
    }

    @Override
    public int getCountOfTargetStacks() {
        return 4;
    }

    @Override
    public CardStack createShuffledCardStack() {
        CardStack stack = new DrawingCardStack(this.getCountOfCardsOfSameColor() * this.getCountOfTargetStacks());

        ArrayList<Card> cards = new ArrayList<>(this.getCountOfCardsOfSameColor() * this.getCountOfTargetStacks());

        cards.addAll(this.generateCards(Card.Color.CLUBS));
        cards.addAll(this.generateCards(Card.Color.DIAMONDS));
        cards.addAll(this.generateCards(Card.Color.HEARTS));
        cards.addAll(this.generateCards(Card.Color.SPADES));

        Collections.shuffle(cards);

        stack.put(cards.toArray(new CardInterface[0]));

        return stack;
    }

    @Override
    public DrawingCardStack createEmptyDrawingStack() {
        return new DrawingCardStack(this.getCountOfCardsOfSameColor() * this.getCountOfTargetStacks());
    }

    @Override
    public CardStackInterface createWastingStack() {
        return new WastingStack(this.getCountOfCardsOfSameColor() * this.getCountOfTargetStacks());
    }


    @Override
    public TargetCardStackInterface createTargetStack(Card.Color color) {
        return new TargetCardStack(this.getCountOfCardsOfSameColor(), color);
    }

    @Override
    public CardStack createWorkingStack() {
        return new WorkingCardStack(this.getMaximumNumberOfCardsInWorkingStack());
    }

    @Override
    public CardStackInterface createEmptyCardStack() {
        return new CardStack(this.getMaximumNumberOfCardsInWorkingStack());
    }

    /**
     * Generates all cards with the given color
     *
     * @param color The color of cards to be generated
     * @return List of all cards(from Ace, through 2,6,10, Jack to King) with the given color
     */
    private ArrayList<Card> generateCards(Card.Color color) {
        ArrayList<Card> cards = new ArrayList<>(this.getCountOfCardsOfSameColor());
        for (int i = 1; i <= this.getCountOfCardsOfSameColor(); i++) {
            cards.add(new Card(color, i, false));
        }

        return cards;
    }
}
