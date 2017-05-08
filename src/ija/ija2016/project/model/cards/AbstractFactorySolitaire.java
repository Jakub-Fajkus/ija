package ija.ija2016.project.model.cards;

/**
 * Base class for all card factory classes.
 */
public abstract class AbstractFactorySolitaire {
    /**
     * How many cards of the same color are there in the current card pack.
     * <p>
     * Example: 13 cards for standard 52 card deck without jokers.
     *
     * @return count of cards
     */
    public abstract int getCountOfCardsOfSameColor();

    /**
     * How many facing down cards can be on the working stack.
     *
     * @return count of cards
     */
    public abstract int getMaximumNumberOfCardsFacingDownInWorkingStack();

    /**
     * How many cards may fit into one working stack.
     * <p>
     * Example: For klondike solitaire this equals 13 + 6.
     * <p>
     * Same as {@link #getCountOfCardsOfSameColor()} + {@link #getMaximumNumberOfCardsFacingDownInWorkingStack()}
     *
     * @return count of cards
     */
    public abstract int getMaximumNumberOfCardsInWorkingStack();

    /**
     * How many working stack are there.
     * <p>
     * Example: For klondike it is 7.
     *
     * @return count of cards
     */
    public abstract int getCountOfWorkingStacks();

    /**
     * How many target stacks are there.
     * <p>
     * This may be the same number a the count of all card colors(symbols).
     * <p>
     * Example: 4 for for standard 52 card deck.
     *
     * @return count fo cards
     */
    public abstract int getCountOfTargetStacks();

    /**
     * Create an object representing the card deck and shuffles the cards.
     *
     * @return Shuffled card deck
     */
    public abstract CardStackInterface createShuffledCardStack();


    /**
     * Create an empty wasting stack.
     *
     * @return Empty wasting stack
     */
    public abstract CardStackInterface createWastingStack();

    /**
     * Create an object representing a target stack.
     * <p>
     * The player's aim is to move all cards to the target stack.
     *
     * @param color Color of the target stack. Cards with any other color will not be accepted.
     * @return Empty working stack
     */
    public abstract TargetCardStackInterface createTargetStack(CardInterface.Color color);

    /**
     * Create an object representing a working stack.
     *
     * @return Empty working stack
     */
    public abstract CardStackInterface createWorkingStack();

    /**
     * Create an empty card stack.
     * <p>
     * The stack is not representing any game object(e.g. drawing, wasting, target) but can be used for support.
     *
     * @return Empty card stack
     */
    public abstract CardStackInterface createEmptyCardStack();

    /**
     * Create an empty drawing stack.
     *
     * @return Empty drawing stack
     */
    public abstract DrawingCardStack createEmptyDrawingStack();

}
