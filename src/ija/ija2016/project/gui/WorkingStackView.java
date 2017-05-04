package ija.ija2016.project.gui;

import ija.ija2016.project.model.cards.CardDeckInterface;
import ija.ija2016.project.model.cards.WorkingCardStack;
import javafx.scene.layout.StackPane;

public class WorkingStackView extends StackPane {
    private CardDeckInterface stack;

    public WorkingStackView (CardDeckInterface stack) {
        this.stack = stack;
        this.setHeight(145);
        this.setWidth(100);

        for (int i = 0; i < stack.size(); i++) {
            this.getChildren().add(new CardView(this.stack.pop()));
        }
    }
}
