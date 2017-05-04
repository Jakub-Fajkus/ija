package ija.ija2016.project.gui;

import ija.ija2016.project.model.cards.Card;
import ija.ija2016.project.model.cards.CardInterface;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import java.io.File;

public class CardView extends ImageView {
    private CardInterface card;

    public CardView(CardInterface card) {
        super();
        this.card = card;

//        File file = new File();                   gui/pr/2016/ija/src/
        System.out.print(getClass().getResource("../../../2C.png"));
//        Image image = new Image(file.toURI().toString());
//        this.setImage(image);
//        this.setFitHeight(145);
//        this.setFitWidth(100);
    }
}
