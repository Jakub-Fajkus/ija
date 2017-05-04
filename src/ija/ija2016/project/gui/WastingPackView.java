package ija.ija2016.project.gui;

import ija.ija2016.project.model.cards.CardDeckInterface;
import javafx.scene.layout.StackPane;

public class WastingPackView extends GuiStackPane {


    public WastingPackView(CardDeckInterface pack) {
        super(pack);
        this.setHeight(145);
        this.setWidth(100);

        for (int i = 0; i < pack.size(); i++) {
            this.getChildren().add(new CardView(this.getPack().get(i),this));
        }
    }
}