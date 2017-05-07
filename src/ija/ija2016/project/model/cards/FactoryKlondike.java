package ija.ija2016.project.model.cards;

import java.util.ArrayList;
import java.util.Collections;

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
    public int getCountOfTargetDecks() {
        return 4;
    }

    /**
     * Vytváří objekt reprezentující balíček karet a zamicha jej.
     *
     * @return
     */
    @Override
    public CardDeck createShuffledCardDeck() {
        return this.createCardDeck();
    }

    private CardDeck createCardDeck() {
        CardDeck deck = new DrawingCardDeck(this.getCountOfCardsOfSameColor() * this.getCountOfTargetDecks());

        ArrayList<Card> cards = new ArrayList<>(this.getCountOfCardsOfSameColor() * this.getCountOfTargetDecks());

        cards.addAll(this.generateCards(Card.Color.CLUBS));
        cards.addAll(this.generateCards(Card.Color.DIAMONDS));
        cards.addAll(this.generateCards(Card.Color.HEARTS));
        cards.addAll(this.generateCards(Card.Color.SPADES));

        Collections.shuffle(cards);

        deck.put(cards.toArray(new CardInterface[0]));

        return deck;
    }


    /**
     * Create an empty drawing deck
     *
     * @return
     */
    public DrawingCardDeck createEmptyDrawindDeck() {
        return new DrawingCardDeck(this.getCountOfCardsOfSameColor() * this.getCountOfTargetDecks());
    }

    /**
     * Vytvori prazdny odkladaci balicek karet
     *
     * @return
     */
    @Override
    public CardDeckInterface createWastingDeck() {
        return new WastingDeck(this.getCountOfCardsOfSameColor() * this.getCountOfTargetDecks());
    }


    /**
     * Vytváří objekt reprezentující cílový balíček. Cílem hráče je vložit všechny karty zadané barvy do cílového balíčku.
     *
     * @param color
     * @return
     */
    @Override
    public TargetCardDeckInterface createTargetPack(Card.Color color) {
        return new TargetCardDeck(this.getCountOfCardsOfSameColor(), color);
    }

    /**
     * Vytváří objekt reprezentující pracovní pole pro karty.
     *
     * @return
     */
    @Override
    public CardStack createWorkingPack() {
        return new WorkingCardStack(this.getMaximumNumberOfCardsInWorkingStack());
    }

    @Override
    public CardStackInterface createEmptyCardStack() {
        return new CardStack(this.getMaximumNumberOfCardsInWorkingStack());
    }

    private ArrayList<Card> generateCards(Card.Color color) {
        ArrayList<Card> cards = new ArrayList<>(this.getCountOfCardsOfSameColor());
        for (int i = 1; i <= this.getCountOfCardsOfSameColor(); i++) {
            cards.add(new Card(color, i, false));
        }

        return cards;
    }
}
