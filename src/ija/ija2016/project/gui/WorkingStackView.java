package ija.ija2016.project.gui;

import ija.ija2016.project.model.cards.CardDeckInterface;
public class WorkingStackView extends GuiStackPane {

    public WorkingStackView (CardDeckInterface pack) {
        super(pack);
        this.setHeight(367);
        this.setWidth(100);


        for (int i = 0; i < pack.size(); i++) {
            CardView card = new CardView(this.getPack().get(i), this);
            card.setTranslateY(20*i);
            card.setOffset(20*i);
            this.getChildren().add(card);
        }
    }


}
