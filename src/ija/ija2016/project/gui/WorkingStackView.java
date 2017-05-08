package ija.ija2016.project.gui;

import ija.ija2016.project.game.GameInterface;
import ija.ija2016.project.model.cards.CardStackInterface;

public class WorkingStackView extends GuiStackPane {
    private int orderNumber;

    public WorkingStackView(CardStackInterface pack, GameInterface game, CardPool cardPool, int orderNumber) {
        super(pack, game, cardPool);
        this.orderNumber = orderNumber;
        this.setHeight(356);
        this.setWidth(82);

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

            cardView.setTranslateY(15 * i);
            cardView.setOffset(15 * i);
            cardView.setContainingElement(this);

            this.getChildren().add(cardView);
        }
    }

    @Override
    public void updateOnGameChange() {
        this.pack = this.game.getWorkingCardStacks()[this.orderNumber];

        super.updateOnGameChange();
    }

    @Override
    public String toString() {
        return "WorkingStackView{" +
                "orderNumber=" + this.orderNumber +
                '}';
    }
}
