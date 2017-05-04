package ija.ija2016.project.gui;

import com.sun.deploy.uitoolkit.DragContext;
import ija.ija2016.project.model.cards.CardInterface;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.paint.Color;


public class CardView extends ImageView{
    private CardInterface card;
    private GuiStackPane parent;
    public static final DataFormat CARD = new DataFormat("Card");
    private double offset = 0;

    public CardView(CardInterface card, GuiStackPane parent) {
        super();
        this.card = card;
        this.parent = parent;

        Image image;
        if (this.card.isTurnedFaceUp()) {
            image = new Image(getClass().getResource("./img/" + this.card.getValueAsString() + this.card.color() + ".png").toString());
        } else {
            image = new Image(getClass().getResource("./img/FD.png").toString());
        }

        DropShadow ds = new DropShadow(5, Color.GREEN);
        this.setEffect(ds);
        this.setImage(image);
        this.setFitHeight(145);
        this.setFitWidth(100);

        this.addEventHandler(MouseEvent.DRAG_DETECTED, this::dragDetected);
        this.addEventHandler(DragEvent.DRAG_OVER, this::dragOver);

        this.makeDraggable(this);
    }

    public void setOffset(double offset) {
        this.offset = offset;
    }

    public double getOffset() {
        return offset;
    }


    final DragContext dragContext = new DragContext();

    private void makeDraggable(final Node node) {
        node.setOnMousePressed(this::handleOnMousePressed);
        node.setOnMouseDragged(this::handleOnMouseDragged);
        node.setOnMouseReleased(this::handleOnMouseReleased);
        node.setOnDragDone(this::handleOnDragDone);
    }


    private void handleOnMousePressed(MouseEvent event) {
        dragContext.x = event.getSceneX();
        dragContext.y = event.getSceneY() - this.offset;
    }

    private void handleOnMouseDragged(MouseEvent event) {

        Node node = (Node) event.getSource();

        double offsetX = event.getSceneX() - dragContext.x;
        double offsetY = event.getSceneY() - dragContext.y;

        node.getParent().toFront();
        node.setTranslateX(offsetX);
        node.setTranslateY(offsetY);
    }

    private void handleOnDragDone(DragEvent event) {

        Node node = (Node) event.getSource();

        double offsetX = event.getSceneX() - dragContext.x;
        double offsetY = event.getSceneY() - dragContext.y;

        node.getParent().toFront();
        node.setTranslateX(offsetX);
        node.setTranslateY(offsetY);
    }

    private void handleOnMouseReleased(MouseEvent event) {

        Node node = (Node) event.getSource();
//        System.out.print(node.toString());

        moveToSource(node);
    }

    private void dragOver(DragEvent e) {
        GuiStackPane stack = (GuiStackPane) e.getSource();
        System.out.println("Over stack drag: " + stack.toString() + "\n");
    }


    private void moveToSource(Node node) {
        //TODO call game.move...
        node.setTranslateX(0);
        node.setTranslateY(0 + offset);
    }

    class DragContext {
        double x, y;
    }

    private void dragDetected(MouseEvent event){

        System.out.print("Draged from stack: " + parent.toString() + "\n");
//        System.out.print("Count of cards in stack: "+parent.getChildren().size()+"\n");
//        System.out.print("Offset of dragged card: "+this.getOffset()+"\n");
//        System.out.print("Poradi karty: "+((this.getOffset()/20)+1.0)+"\n");
        System.out.print("Pocet karet: " + (parent.getChildren().size() - ((this.getOffset() / 20))) + "\n");

        // TODO madafaka nejde to...
        // Java Messsge:ija.ija2016.project.gui.CardView cannot be cast to ija.ija2016.project.gui.GuiStackPane
        // ne nechapu to
        if (!this.card.isTurnedFaceUp())
            return;

        Dragboard dragboard = this.startDragAndDrop(TransferMode.MOVE);
        ClipboardContent content = new ClipboardContent();
        content.putString("card");
        dragboard.setContent(content);
        event.consume();
    }
}