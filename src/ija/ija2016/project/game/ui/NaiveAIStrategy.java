package ija.ija2016.project.game.ui;

import ija.ija2016.project.game.GameInterface;
import ija.ija2016.project.game.command.MoveCommandInterface;
import ija.ija2016.project.game.command.MoveGameCommand;
import ija.ija2016.project.model.cards.CardDeckInterface;
import ija.ija2016.project.model.cards.CardInterface;

import java.util.ArrayList;

/**
 * This strategy tries to find a move in the current game state. It's aim is to move cards to the target stacks.
 * <p>
 * The strategy does those steps:
 * - check if any card from the working can be moved to the targets
 * - check if the card from the wasting could be moved to the targets
 * - check if any card can be moved between a working and a working
 * - check if more cards can be moved between a working and a working
 */
public class NaiveAIStrategy implements AIStrategyInterface {
    private ArrayList<MoveCommandInterface> commands;
    private GameInterface game;

    /**
     * Get all possible moves for the game
     *
     * @param game
     * @return
     */
    @Override
    public ArrayList<MoveCommandInterface> getPossibleMoves(GameInterface game) {
        this.commands = new ArrayList<>();
        this.game = game;

        //check if any card from the working can be moved to the targets
        for (int i = 0; i < game.getWorkingCardStacks().length; i++) {
            CardDeckInterface stack = game.getWorkingCardStacks()[i];
            CardInterface card = stack.get();

            this.tryToMoveToTargets(stack, card);
        }

        //check if the card from the wasting could be moved to the targets
        CardInterface card = game.getWastingDeck().get();
        if (card != null) {
            tryToMoveToTargets(game.getWastingDeck(), card);
            card = null;
        }

        //check if any card can be moved between a working and a working
        for (int i = 0; i < game.getWorkingCardStacks().length; i++) {
            CardDeckInterface source = game.getTargetPacks()[i];
            CardInterface card1 = source.get();

            for (int j = 0; i != j && j < game.getWorkingCardStacks().length; j++) {
                CardDeckInterface target = game.getTargetPacks()[j];

                if (target.put(card1)) {
                    this.commands.add(new MoveGameCommand(source, target, 1));
                    target.pop();
                }
            }
        }

        //check if more cards can be moved between a working and a working
        //for each source deck
        for (int sourceIndex = 0; sourceIndex < game.getWorkingCardStacks().length; sourceIndex++) {
            CardDeckInterface source = game.getTargetPacks()[sourceIndex];

            //for each destination deck, excluding the source
            for (int destinationIndex = 0; sourceIndex != destinationIndex && destinationIndex < game.getWorkingCardStacks().length; destinationIndex++) {
                CardDeckInterface target = game.getTargetPacks()[destinationIndex];

                //try to move the biggest possible number of cards
                for (int numberOfCardsToMove = source.getNumberOfCardsFacingUp() - 1; numberOfCardsToMove >= 0; numberOfCardsToMove--) {
                    CardInterface cards[] = new CardInterface[numberOfCardsToMove];

                    for (int i = 0; i < numberOfCardsToMove; i++) {
                        cards[i] = source.get(source.size() - i - 1);
                    }

                    boolean success = true;
                    //try to move all cards
                    for (int actualMovingCardIndex = 0; actualMovingCardIndex < numberOfCardsToMove; actualMovingCardIndex++) {
                        if (!target.put(cards[actualMovingCardIndex])) {
                            //return all cards - the last card was not added successfully(thus -1)
                            for (int i = 0; i < actualMovingCardIndex - 1; i++) {
                                target.pop();
                            }
                            success = false;
                            break;
                        }
                    }

                    if (success) {
                        this.commands.add(new MoveGameCommand(source, target, numberOfCardsToMove));
                        target.pop();
                        break;
                    }
                }
            }
        }

        ArrayList<MoveCommandInterface> returnCommands = this.commands;
        this.commands = null;
        this.game = null;

        return returnCommands;
    }

    private void tryToMoveToTargets(CardDeckInterface source, CardInterface card) {
        for (int j = 0; j < game.getTargetPacks().length; j++) {
            CardDeckInterface target = game.getTargetPacks()[j];
            if (target.put(card)) {
                this.commands.add(new MoveGameCommand(source, target, 1));
                target.pop();
            }
        }
    }
}