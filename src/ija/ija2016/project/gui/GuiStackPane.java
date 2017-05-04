package ija.ija2016.project.gui;

import ija.ija2016.project.model.cards.CardDeckInterface;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.StackPane;

abstract public class GuiStackPane extends StackPane {
    private CardDeckInterface pack;

    public GuiStackPane(CardDeckInterface pack) {
        this.pack = pack;
//        this.addEventHandler(MouseEvent.MOUSE_RELEASED, this::mouseEntered);
        this.addEventHandler(DragEvent.DRAG_DROPPED, this::dragDropped);
        this.addEventHandler(DragEvent.DRAG_OVER, this::dragOverWorking);
        this.setOnMouseDragEntered(this::mouseEntered);
    }

    public CardDeckInterface getPack() {
        return pack;
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
        if (dragboard.hasString()){
            event.acceptTransferModes(TransferMode.MOVE);
        }
        event.consume();
    }
}
