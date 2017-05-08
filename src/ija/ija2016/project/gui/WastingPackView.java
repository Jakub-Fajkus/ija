/*
 * IJA2016 Solitaire
 * Jakub Fajkus & David Czernin
 */

package ija.ija2016.project.gui;

import ija.ija2016.project.game.GameInterface;
import ija.ija2016.project.model.cards.CardStackInterface;

public class WastingPackView extends GuiStackPane {

    public WastingPackView(CardStackInterface pack, GameInterface game, CardPool cardPool) {
        super(pack, game, cardPool);
        this.setHeight(120);
        this.setWidth(82);

        this.redrawCards();
    }


    @Override
    public void updateOnGameChange() {
        this.pack = this.game.getWastingDeck();

        super.updateOnGameChange();
    }

    @Override
    public String toString() {
        return "WastingPackView{}";
    }
}