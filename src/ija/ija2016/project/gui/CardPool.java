package ija.ija2016.project.gui;

import ija.ija2016.project.model.cards.CardInterface;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

public class CardPool {
    private Hashtable<String, CardView> cards;
    private CardImageStorage imageStorage;


    public CardPool(ArrayList<CardInterface> cards) {
        this.cards = new Hashtable<>(cards.size());
        this.imageStorage = CardImageStorage.getInstance(cards);

        //create all card views!
        for (CardInterface card : cards) {
            CardView cardView = new CardView(card, null);
            this.cards.put(this.getCardString(card), cardView);
        }
    }

    public CardView getCardView(CardInterface card) {
        CardView view = this.cards.get(this.getCardString(card));

        if (view != null) {
            if (card.isTurnedFaceUp()) {
                view.init(this.imageStorage.get(card));
            } else {
                view.init(this.imageStorage.getFacingDownImage());
            }

//            view.removeShadow();
        } else {
            System.out.println("Card view is null!");
        }

        return view;
    }

    private String getCardString(CardInterface card) {
        return card.getValueAsString() + card.color();
    }

    public void updateCards(ArrayList<CardInterface> cards) {
        for (Map.Entry<String, CardView> entry : this.cards.entrySet()) {
            CardView value = entry.getValue();

            //find a card with the values as the current value has
            for (CardInterface card : cards) {
                if (card.color() == value.getCard().color() && card.value() == value.getCard().value()) {
                    value.setCard(card);
                    value.removeShadow();
                }
            }
        }
    }
}
