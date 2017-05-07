package ija.ija2016.project.game;

import ija.ija2016.project.model.cards.*;

import java.io.Serializable;
import java.util.ArrayList;

public class GameState implements Serializable {
    private TargetCardDeckInterface[] targetPacks;
    private CardDeckInterface drawingDeck;
    private CardDeckInterface wastingDeck;
    private CardStackInterface[] workingCardStacks;
    private ArrayList<CardInterface> allCards;

    /**
     * Create a game state from the given stacks
     * <p>
     * The decks will be directly stored - no copying is performed
     *
     * @param targetPacks
     * @param drawingDeck
     * @param wastingDeck
     * @param workingCardStacks
     */
    public GameState(TargetCardDeckInterface[] targetPacks, CardDeckInterface drawingDeck, CardDeckInterface wastingDeck, CardStackInterface[] workingCardStacks) {
        this.targetPacks = targetPacks;
        this.drawingDeck = drawingDeck;
        this.wastingDeck = wastingDeck;
        this.workingCardStacks = workingCardStacks;
        this.allCards = new ArrayList<>();
        this.initAllCards();
    }

    /**
     * Copying constructor - the state given will be copied in this state
     *
     * @param state
     */
    public GameState(GameState state) {
        FactoryKlondike factory = new FactoryKlondike();

        this.allCards = new ArrayList<>();
        this.drawingDeck = factory.createEmptyDrawindDeck();
        this.wastingDeck = factory.createWastingDeck();

        this.targetPacks = new TargetCardDeckInterface[state.getTargetPacks().length];
        for (int i = 0; i < this.targetPacks.length; i++) {
            this.targetPacks[i] = factory.createTargetPack(state.getTargetPacks()[i].getColorOfDeck());
        }

        this.workingCardStacks = new CardStackInterface[state.getWorkingCardStacks().length];

        for (int i = 0; i < this.workingCardStacks.length; i++) {
            this.workingCardStacks[i] = factory.createWorkingPack();
        }

        this.initFrom(state);
    }

    /**
     * Initialize the current state from the given state
     * <p>
     * The references to the stacks will not be changed. Only their content will be changed.
     *
     * @param state
     */
    public void initFrom(GameState state) {
        //remove all cards
        for (TargetCardDeckInterface targetPack : this.targetPacks) {
            targetPack.removeAll();
        }

        for (CardStackInterface workingCardStack : this.workingCardStacks) {
            workingCardStack.removeAll();
        }

        this.drawingDeck.removeAll();
        this.wastingDeck.removeAll();


        //copy all cards for all working stacks
        this.copyAllCards(state.getWorkingCardStacks(), this.getWorkingCardStacks());

        //copy all cards for all target stacks
        this.copyAllCards(state.getTargetPacks(), this.getTargetPacks());

        //copy all cards from drawing and wasting
        this.putAllCardsTo(state.getDrawingDeck().getAll(), this.getDrawingDeck());
        this.putAllCardsTo(state.getWastingDeck().getAll(), this.getWastingDeck());

        this.initAllCards();
    }

    /**
     * Fill the inner list of cards with the cards from all stacks
     */
    private void initAllCards() {
        this.allCards = new ArrayList<>();

        for (CardDeckInterface stack : this.workingCardStacks) {
            this.allCards.addAll(stack.getAll());
        }

        for (CardDeckInterface stack : this.targetPacks) {
            this.allCards.addAll(stack.getAll());
        }

        this.allCards.addAll(this.getDrawingDeck().getAll());
        this.allCards.addAll(this.getWastingDeck().getAll());
    }


    public TargetCardDeckInterface[] getTargetPacks() {
        return this.targetPacks;
    }

    public CardDeckInterface getDrawingDeck() {
        return this.drawingDeck;
    }

    public CardDeckInterface getWastingDeck() {
        return this.wastingDeck;
    }

    public CardStackInterface[] getWorkingCardStacks() {
        return this.workingCardStacks;
    }

    public ArrayList<CardInterface> getAllCards() {
        return this.allCards;
    }

    /**
     * Copy all cards from the sources to targets
     * <p>
     * The length of both arrays must be the same
     * The method copies all cards from the source on index I to destination on index I
     *
     * @param source
     * @param destination
     */
    private void copyAllCards(CardDeckInterface[] source, CardDeckInterface[] destination) {
        for (int i = 0; i < source.length; i++) {
            this.putAllCardsTo(source[i].getAll(), destination[i]);
        }
    }

    /**
     * Put all cards to the dest.
     * <p>
     * The method saves all the cards to the history game state
     *
     * @param cards
     * @param dest
     */
    private void putAllCardsTo(ArrayList<CardInterface> cards, CardDeckInterface dest) {
        for (CardInterface card : cards) {
            CardInterface newCard = new Card(card);
            this.saveCardToHistory(newCard);

            dest.forcePut(newCard);
        }
    }

    private void saveCardToHistory(CardInterface card) {
        this.getAllCards().add(card);
    }
}
