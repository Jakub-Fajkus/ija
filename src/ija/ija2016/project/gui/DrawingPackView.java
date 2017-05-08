/*
 * IJA2016 Solitaire
 * Jakub Fajkus & David Czernin
 */

package ija.ija2016.project.gui;

import ija.ija2016.project.game.GameInterface;
import ija.ija2016.project.model.cards.CardStackInterface;

public class DrawingPackView extends GuiStackPane {

    public DrawingPackView(CardStackInterface pack, GameInterface game, CardPool cardPool) {
        super(pack, game, cardPool);
        this.setHeight(145);
        this.setWidth(100);

        this.redrawCards();
    }

    @Override
    public void updateOnGameChange() {
        this.pack = this.game.getDrawingDeck();

        super.updateOnGameChange();
    }
}
