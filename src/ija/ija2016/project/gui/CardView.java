package ija.ija2016.project.gui;

import ija.ija2016.project.model.cards.CardInterface;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DataFormat;
import javafx.scene.paint.Color;


public class CardView extends ImageView {
    public static final DataFormat CARD = new DataFormat("Card");
    final DragContext dragContext = new DragContext();
    private CardInterface card;
    private GuiStackPane containingElement;
    private double offset = 0;

    public CardView(CardInterface card, GuiStackPane containingElement) {
        super();
        this.card = card;
        this.containingElement = containingElement;

        this.init(null);
    }

    public void init(Image image) {
        DropShadow ds = new DropShadow(5, Color.GREEN);
        this.setEffect(ds);
        this.setImage(image);
        this.setFitHeight(145);
        this.setFitWidth(100);
        this.setStyle("-fx-cursor: hand;");
        this.offset = 0;
    }

    public double getOffset() {
        return offset;
    }

    public void setOffset(double offset) {
        this.offset = offset;
    }

    public GuiStackPane getContainingElement() {
        return containingElement;
    }

    public void setContainingElement(GuiStackPane containingElement) {
        this.containingElement = containingElement;
    }

    public boolean isTurnedFaceUp() {
        return this.card.isTurnedFaceUp();
    }

    public CardInterface getCard() {
        return card;
    }

    public void setCard(CardInterface card) {
        this.card = card;
    }

    //    private void makeDraggable(final Node node) {
//        node.setOnMousePressed(this::handleOnMousePressed);
//        node.setOnMouseDragged(this::handleOnMouseDragged);
//        node.setOnMouseReleased(this::handleOnMouseReleased);
//        node.setOnDragDone(this::handleOnDragDone);
//    }
//
//
//    private void handleOnMousePressed(MouseEvent event) {
//        System.out.println("handleOnMousePressed");
//        dragContext.x = event.getSceneX();
//        dragContext.y = event.getSceneY() - this.offset;
//    }
//
//    private void handleOnMouseDragged(MouseEvent event) {
//        System.out.println("handleOnMouseDragged");
//        Node node = (Node) event.getSource();
//
//        double offsetX = event.getSceneX() - dragContext.x;
//        double offsetY = event.getSceneY() - dragContext.y;
//
//        node.getParent().toFront();
//        node.setTranslateX(offsetX);
//        node.setTranslateY(offsetY);
//    }
//
//    private void handleOnDragDone(DragEvent event) {
//
//        Node node = (Node) event.getSource();
//
//        double offsetX = event.getSceneX() - dragContext.x;
//        double offsetY = event.getSceneY() - dragContext.y;
//
//        node.getParent().toFront();
//        node.setTranslateX(offsetX);
//        node.setTranslateY(offsetY);
//    }
//
//    private void handleOnMouseReleased(MouseEvent event) {
//        System.out.println("handleOnMouseReleased on stack");
//        Node node = (Node) event.getSource();
////        System.out.print(node.toString());
//
//        moveToSource(node);
//    }
//
//    private void dragOver(DragEvent e) {
//        if (e.getSource() instanceof CardView) {
//            CardView cardView = (CardView)e.getSource();
//            GuiStackPane stack = cardView.containingElement;
//            System.out.println("Over stack drag: " + stack.toString() + "\n");
//        } else {
//            System.out.print("Drag over: " + e.getClass());
//        }
//    }
//
//
//    private void moveToSource(Node node) {
//        //TODO call game.move...
//        node.setTranslateX(0);
//        node.setTranslateY(0 + offset);
//    }
//
//    private void dragDetected(MouseEvent event){
//
//        System.out.print("Draged from stack: " + containingElement.toString() + "\n");
////        System.out.print("Count of cards in stack: "+containingElement.getChildren().size()+"\n");
////        System.out.print("Offset of dragged card: "+this.getOffset()+"\n");
////        System.out.print("Poradi karty: "+((this.getOffset()/20)+1.0)+"\n");
//        System.out.print("Pocet karet: " + (containingElement.getChildren().size() - ((this.getOffset() / 20))) + "\n");
//
//        // TODO madafaka nejde to...
//        // Java Messsge:ija.ija2016.project.gui.CardView cannot be cast to ija.ija2016.project.gui.GuiStackPane
//        // ne nechapu to
//        if (!this.card.isTurnedFaceUp())
//            return;
//
//        Dragboard dragboard = this.startDragAndDrop(TransferMode.MOVE);
//        ClipboardContent content = new ClipboardContent();
//        content.putString("card");
//        dragboard.setContent(content);
//        event.consume();
//    }
}