package ija.ija2016.project.gui;

import ija.ija2016.project.model.cards.CardInterface;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Hashtable;

public class CardImageStorage {
    public static final String FACING_DOWN_IMAGE = "FD";
    private static CardImageStorage ourInstance;
    private Hashtable<String, Image> images;

    private CardImageStorage(ArrayList<CardInterface> cards) {
        this.images = new Hashtable<>(cards.size());

        //create all card views!
        for (CardInterface card : cards) {
            //add image for the card
            Image image = new Image(getClass().getResource("./img/" + this.getCardString(card) + ".png").toString());
            this.images.put(this.getCardString(card), image);
        }

        //add facing down image
        this.images.put(FACING_DOWN_IMAGE, new Image(getClass().getResource("./img/FD.png").toString()));
    }

    public static CardImageStorage getInstance(ArrayList<CardInterface> cards) {
        if (ourInstance == null) {
            ourInstance = new CardImageStorage(cards);
        }

        return ourInstance;
    }

    private String getCardString(CardInterface card) {
        return card.getValueAsString() + card.color();
    }

    public Image get(CardInterface card) {
        return this.images.get(this.getCardString(card));
    }

    public Image getFacingDownImage() {
        return this.images.get(FACING_DOWN_IMAGE);
    }

}
