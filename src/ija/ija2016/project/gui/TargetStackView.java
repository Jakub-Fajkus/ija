/*
 * IJA2016 Solitaire
 * Jakub Fajkus & David Czernin
 */

package ija.ija2016.project.gui;

import ija.ija2016.project.game.GameInterface;
import ija.ija2016.project.model.cards.CardStackInterface;

public class TargetStackView extends GuiStackPane {
    private int orderNumber;

    public TargetStackView(CardStackInterface pack, GameInterface game, CardPool cardPool, int orderNumber) {
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

