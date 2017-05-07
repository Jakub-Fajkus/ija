package ija.ija2016.project.gui;

import ija.ija2016.project.game.GameInterface;
import ija.ija2016.project.model.cards.CardDeckInterface;

public class TargetStackView extends GuiStackPane {
    private int orderNumber;

    public TargetStackView(CardDeckInterface pack, GameInterface game, CardPool cardPool, int orderNumber) {
        super(pack, game, cardPool);
        this.orderNumber = orderNumber;

        this.setHeight(120);
        this.setWidth(82);

        this.redrawCards();
    }


    @Override
    public void updateOnGameChange() {
        this.pack = this.game.getTargetPacks()[this.orderNumber];

        super.updateOnGameChange();
    }

    @Override
    public String toString() {
        return "TargetStackView{" +
                "orderNumber=" + this.orderNumber +
                '}';
    }
}

