package ija.ija2016.project.gui;

import ija.ija2016.project.game.GameInterface;
import ija.ija2016.project.model.cards.CardDeckInterface;

public class DrawingPackView extends GuiStackPane {

    public DrawingPackView(CardDeckInterface pack, GameInterface game, CardPool cardPool) {
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
