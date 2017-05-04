package ija.ija2016.project.gui;

import ija.ija2016.project.game.*;
import ija.ija2016.project.model.cards.Card;
import ija.ija2016.project.model.cards.CardInterface;
import ija.ija2016.project.model.cards.WorkingCardStack;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;


public class GameController implements Initializable {

    @FXML
    public GridPane main_window;
    public GridPane working_pane;

    private WorkingStackView wstack1, wstack2, wstack3, wstack4, wstack5, wstack6, wstack7;

    private GameInterface game;

    public GameController() {
        main_window = new GridPane();
        working_pane = new GridPane();


        this.game =(new GameFactory().createGame());
        wstack1 = new WorkingStackView(this.game.getWorkingCardStacks()[0]);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        this.createGame();
    }

    @FXML
    public void createGame(){

        try{
            working_pane.add(this.wstack1,2,0);
        } catch (Exception e){
            e.printStackTrace();
            System.out.print("fail create stack");
        }


//        try {
//            wstack1 = new WorkingCardStack(24);
//            wstack1.put(new Card(CardInterface.Color.CLUBS, 12, false));
//        } catch (Exception e) {
//            System.out.print("fail create stack");
//        }


    }
}
