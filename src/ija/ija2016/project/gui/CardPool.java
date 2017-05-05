package ija.ija2016.project.gui;

import ija.ija2016.project.model.cards.CardInterface;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

public class CardPool {
    public static final String FACING_DOWN_IMAGE = "FD";
    private Hashtable<String, CardView> cards;
    private Hashtable<String, Image> images;


    public CardPool(ArrayList<CardInterface> cards) {
        this.cards = new Hashtable<>(cards.size());
        this.images = new Hashtable<>(cards.size());

        //create all card views!
        for (CardInterface card : cards) {
            CardView cardView = new CardView(card, null);
            this.cards.put(this.getCardString(card), cardView);

            //add image for the card
            Image image = new Image(getClass().getResource("./img/" + this.getCardString(card) + ".png").toString());
            this.images.put(this.getCardString(card), image);
        }

        //add facing down image
        this.images.put(FACING_DOWN_IMAGE, new Image(getClass().getResource("./img/FD.png").toString()));
    }

    public CardView getCardView(CardInterface card) {
        CardView view = this.cards.get(this.getCardString(card));

        if (view != null) {
            if (card.isTurnedFaceUp()) {
                if (this.images.get(this.getCardString(card)) == null) {
                    System.out.println("AAAHA, you bitch");
                }
                view.init(this.images.get(this.getCardString(card)));
            } else {
                view.init(this.images.get(FACING_DOWN_IMAGE));
            }
        } else {
            System.out.println("Card view is nul!");
        }

        return view;
    }

    private String getCardString(CardInterface card) {
        return card.getValueAsString() + card.color();
    }

    public void updateCards(ArrayList<CardInterface> cards) {
        for (Map.Entry<String, CardView> entry : this.cards.entrySet()) {
            String key = entry.getKey();
            CardView value = entry.getValue();

            //find a card with the values as the current value has
            for (CardInterface card : cards) {
                if (card.color() == value.getCard().color() && card.value() == value.getCard().value()) {
                    value.setCard(card);
                }

            }
        }

//        for(CardView view : this.cards.)

    }
}
