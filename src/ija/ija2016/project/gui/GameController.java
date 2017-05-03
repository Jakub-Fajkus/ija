package ija.ija2016.project.gui;


import ija.ija2016.project.game.cards.*;
import ija.ija2016.project.game.cards.Card;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

import ija.ija2016.project.model.*;
import javafx.scene.layout.GridPane;


public class GameController implements Initializable {

    @FXML
    public GridPane main_window;
    public GridPane working_pane;



    private WorkingCardStack wstack1, wstack2, wstack3, wstack4, wstack5, wstack6, wstack7;

    public GameController() {
        this.createGame();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void createGame(){
        wstack1 =
        wstack1.put(new Card(Card.Color.CLUBS, 2, true));

    }
}
