package ija.ija2016.project.gui;

import ija.ija2016.project.game.GameInterface;
import ija.ija2016.project.game.GameObserverInterface;
import ija.ija2016.project.model.cards.CardDeckInterface;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.StackPane;

abstract public class GuiStackPane extends StackPane implements GameObserverInterface {
    CardPool cardPool;
    CardDeckInterface pack;
    GameInterface game;

    GuiStackPane(CardDeckInterface pack, GameInterface game, CardPool cardPool) {
        this.pack = pack;
        this.game = game;
        this.cardPool = cardPool;


        this.addEventHandler(DragEvent.DRAG_DROPPED, this::dragDropped);
        this.addEventHandler(DragEvent.DRAG_OVER, this::dragOverWorking);
        this.setOnMouseDragEntered(this::mouseEntered);
    }

    public CardDeckInterface getPack() {
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
                System.out.println("Card view is not in the pool!");
                continue;
            }

            cardView.setTranslateY(0);
            cardView.setOffset(0);
            cardView.setContainingElement(this);

            this.getChildren().add(cardView);
        }
    }

    private void mouseEntered(MouseEvent e) {
        GuiStackPane stack = (GuiStackPane) e.getSource();
        System.out.println("you are in with drag: " + stack.toString() + "\n");
    }

    private void dragDropped(DragEvent e) {
        GuiStackPane stack = (GuiStackPane) e.getSource();
        System.out.println("Over stack drag: " + stack.toString() + "\n");
    }

    private void dragOverWorking(DragEvent event) {
        Dragboard dragboard = event.getDragboard();
        if (dragboard.hasString()) {
            event.acceptTransferModes(TransferMode.MOVE);
        }
        event.consume();
    }

    @Override
    public void updateOnGameChange() {
        this.redrawCards();
    }
}
