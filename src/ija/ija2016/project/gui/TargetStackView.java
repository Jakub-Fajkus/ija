package ija.ija2016.project.gui;

import ija.ija2016.project.game.GameInterface;
import ija.ija2016.project.model.cards.CardDeckInterface;

public class TargetStackView extends GuiStackPane {

    public TargetStackView(CardDeckInterface pack, GameInterface game, CardPool cardPool) {
        super(pack, game, cardPool);
        this.setHeight(145);
        this.setWidth(100);

        this.redrawCards();
    }
}

