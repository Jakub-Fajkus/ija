package ija.ija2016.project.model.cards;

public abstract class AbstractFactorySolitaire {
    /**
     * How many cards of the same color are there in the current card pack.
     * e.g. 13 for standard 52 card deck without jokers
     */
    public abstract int getCountOfCardsOfSameColor();

    /**
     * How many facing down cards can be on the working stack.
     */
    public abstract int getMaximumNumberOfCardsFacingDownInWorkingStack();

    /**
     * How many cards may fit into one working stack.
     * e.g. For klondike solitaire this equals 6 + 13.
     * <p>
     * Should be equal to getCountOfCardsOfSameColor() + getMaximumNumberOfCardsFacingDownInWorkingStack()
     */
    public abstract int getMaximumNumberOfCardsInWorkingStack();

    /**
     * How many working stack are there.
     * e.g. For klondike it is 7.
     */
    public abstract int getCountOfWorkingStacks();

    /**
     * How many target decks are there.
     * <p>
     * This may be the same number a the count of all card colors(symbols).
     * e.g. 4 for for standard 52 card deck
     */
    public abstract int getCountOfTargetDecks();

    /**
     * Vytváří objekt reprezentující balíček karet a zamicha jej.
     *
     * @return
     */
    public abstract CardDeckInterface createShuffledCardDeck();


    /**
     * Vytvori prazdny odkladaci balicek karet
     *
     * @return
     */
    public abstract CardDeckInterface createWastingDeck();

    /**
     * Vytváří objekt reprezentující cílový balíček. Cílem hráče je vložit všechny karty zadané barvy do cílového balíčku.
     *
     * @param color
     * @return
     */
    public abstract TargetCardDeckInterface createTargetPack(CardInterface.Color color);

    /**
     * Vytváří objekt reprezentující pracovní pole pro karty.
     *
     * @return
     */
    public abstract CardStackInterface createWorkingPack();

    public abstract CardStackInterface createEmptyCardStack();

}
