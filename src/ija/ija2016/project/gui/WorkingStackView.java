package ija.ija2016.project.gui;

import ija.ija2016.project.game.GameInterface;
import ija.ija2016.project.model.cards.CardDeckInterface;

public class WorkingStackView extends GuiStackPane {

    public WorkingStackView(CardDeckInterface pack, GameInterface game, CardPool cardPool) {
        super(pack, game, cardPool);
        this.setHeight(367);
        this.setWidth(100);

        this.redrawCards();
    }

    @Override
    protected void redrawCards() {
        this.getChildren().clear();

        for (int i = 0; i < this.getPack().size(); i++) {
            CardView cardView = this.cardPool.getCardView(this.getPack().get(i));
            if (cardView == null) {
                System.out.println("Card view is not in the pool!");
                continue;
            }

            cardView.setTranslateY(20 * i);
            cardView.setOffset(20 * i);
            cardView.setContainingElement(this);

            this.getChildren().add(cardView);
        }
    }
}