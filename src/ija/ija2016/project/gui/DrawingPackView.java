package ija.ija2016.project.gui;

import ija.ija2016.project.model.cards.CardDeckInterface;
import javafx.scene.layout.StackPane;

public class DrawingPackView extends StackPane {
    private CardDeckInterface pack;

    public DrawingPackView(CardDeckInterface pack) {
        this.pack = pack;
        this.setHeight(145);
        this.setWidth(100);

        for (int i = 0; i < pack.size(); i++) {
            this.getChildren().add(new CardView(this.pack.get(i)));
        }
    }
}
