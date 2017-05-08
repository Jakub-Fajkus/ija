/*
 * IJA2016 Solitaire
 * Jakub Fajkus & David Czernin
 */

package ija.ija2016.project.gui;

import ija.ija2016.project.game.GameInterface;
import ija.ija2016.project.game.GameObserverInterface;
import ija.ija2016.project.model.cards.CardStackInterface;
import javafx.scene.layout.StackPane;

abstract public class GuiStackPane extends StackPane implements GameObserverInterface {
    CardPool cardPool;
    CardStackInterface pack;
    GameInterface game;

    GuiStackPane(CardStackInterface pack, GameInterface game, CardPool cardPool) {
        this.pack = pack;
        this.game = game;
        this.cardPool = cardPool;
    }

    public CardStackInterface getPack() {
        return this.pack;
    }

    /**
     * Redraw the cards in the pane
     * <p>
     * This method is calle every time the game state is changed
     */
    void redrawCards() {
        this.getChildren().clear();

        for (int i = 0; i < this.getPack().size(); i++) {
            CardView cardView = this.cardPool.getCardView(this.getPack().get(i));

            if (cardView == null) {
                continue;
            }

            cardView.setTranslateY(0);
            cardView.setOffset(0);
            cardView.setContainingElement(this);

            this.getChildren().add(cardView);
        }
    }

    @Override
    public void updateOnGameChange() {
        this.redrawCards();
    }
}
