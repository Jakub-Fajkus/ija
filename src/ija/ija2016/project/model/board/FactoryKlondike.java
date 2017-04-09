package ija.ija2016.project.model.board;

import ija.ija2016.project.game.cards.Card;
import ija.ija2016.project.game.cards.CardDeck;
import ija.ija2016.project.game.cards.CardStack;
import ija.ija2016.project.game.cards.WorkingCardStack;
import ija.ija2016.project.game.cards.TargetCardDeck;

/**
 * Created by Jakub on 24.03.17.
 */
public class FactoryKlondike extends AbstractFactorySolitaire{
    public static int NumberOfCardsInStack = 13;

    /**
     * Vytvoří objekt reprezentující kartu.
     *
     * @param color
     * @param value
     * @return
     */
    @Override
    public Card createCard(Card.Color color, int value) {
        if (value == 0 || value > NumberOfCardsInStack) {
            return null;
        }

        return new Card(color, value, false);
    }

    /**
     * Vytváří objekt reprezentující balíček karet.
     *
     * @return
     */
    @Override
    public CardDeck createCardDeck() {
        CardDeck deck = new CardDeck(52);
//
        deck.put(this.generateCards(Card.Color.CLUBS));
        deck.put(this.generateCards(Card.Color.DIAMONDS));
        deck.put(this.generateCards(Card.Color.HEARTS));
        deck.put(this.generateCards(Card.Color.SPADES));

        return deck;
    }

    /**
     * Vytváří objekt reprezentující cílový balíček. Cílem hráče je vložit všechny karty zadané barvy do cílového balíčku.
     *
     * @param color
     * @return
     */
    @Override
    public CardDeck createTargetPack(Card.Color color) {
        return new TargetCardDeck(NumberOfCardsInStack, color);
    }

    /**
     * Vytváří objekt reprezentující pracovní pole pro karty.
     *
     * @return
     */
    @Override
    public CardStack createWorkingPack() {
        return new WorkingCardStack(NumberOfCardsInStack);
    }

    private Card[] generateCards(Card.Color color) {
        Card[] cards = new Card[NumberOfCardsInStack];
        for (int i = 1; i <= NumberOfCardsInStack; i++) {
            cards[i-1] = new Card(color, i, false);
        }

        return cards;
    }
}
