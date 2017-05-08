/*
 * IJA2016 Solitaire
 * Jakub Fajkus & David Czernin
 */

package ija.ija2016.project.gui;

import ija.ija2016.project.model.cards.CardInterface;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;


public class CardView extends ImageView {
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
        this.setImage(image);
        this.setFitHeight(115);
        this.setFitWidth(82);
        this.setStyle("-fx-cursor: hand;");
        this.offset = 0;
    }

    public double getOffset() {
        return this.offset;
    }

    public void setOffset(double offset) {
        this.offset = offset;
    }

    public GuiStackPane getContainingElement() {
        return this.containingElement;
    }

    public void setContainingElement(GuiStackPane containingElement) {
        this.containingElement = containingElement;
    }

    public boolean isTurnedFaceUp() {
        return this.card.isTurnedFaceUp();
    }

    public CardInterface getCard() {
        return this.card;
    }

    public void setCard(CardInterface card) {
        this.card = card;
    }

    public void setSelectedShadow() {
        DropShadow ds = new DropShadow(BlurType.ONE_PASS_BOX, new Color(0.3569, 0.5255, 1, 0.8), 20, 10, 0, 0);
        this.setEffect(ds);
    }

    public void removeShadow() {
        this.setEffect(null);
    }

    public void setTipShadow() {
        DropShadow ds = new DropShadow(BlurType.ONE_PASS_BOX, new Color(1, 0.9882, 0.149, 0.8), 20, 10, 0, 0);
        this.setEffect(ds);
    }
}