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
}