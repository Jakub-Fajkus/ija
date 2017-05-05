package ija.ija2016.project.gui;

import ija.ija2016.project.model.cards.CardInterface;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Hashtable;

public class CardPool {
    public static final String FACING_DOWN_IMAGE = "FD";
    private Hashtable<CardInterface, CardView> cards;
    private Hashtable<String, Image> images;


    public CardPool(ArrayList<CardInterface> cards) {
        this.cards = new Hashtable<>(cards.size());
        this.images = new Hashtable<>(cards.size());

        //create all card views!
        for (CardInterface card : cards) {
            CardView cardView = new CardView(card, null);
            this.cards.put(card, cardView);

            //add image for the card
            Image image = new Image(getClass().getResource("./img/" + card.getValueAsString() + card.color() + ".png").toString());
            this.images.put(card.getValueAsString() + card.color(), image);
        }

        //add facing down image
        this.images.put(FACING_DOWN_IMAGE, new Image(getClass().getResource("./img/FD.png").toString()));
    }

    public CardView getCardView(CardInterface card) {
        CardView view = this.cards.get(card);
        if (view != null) {
            if (card.isTurnedFaceUp()) {
//                view.init(this.images.get(card.toString()));
                if (this.images.get(card.getValueAsString() + card.color()) == null) {
                    System.out.println("AAAHA, you bitch");
                }
                view.init(this.images.get(card.getValueAsString() + card.color()));
            } else {
                view.init(this.images.get(FACING_DOWN_IMAGE));
            }
        } else {
            System.out.println("Card view is nul!");
        }

        return view;
    }
}
