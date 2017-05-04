package ija.ija2016.project.gui;

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

    private CardView cardImg;

    private WorkingCardStack wstack1, wstack2, wstack3, wstack4, wstack5, wstack6, wstack7;

    public GameController() {
        main_window = new GridPane();
        working_pane = new GridPane();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        this.cardImg = new CardView(new Card(CardInterface.Color.CLUBS,2, true));
        this.createGame();
    }

    @FXML
    public void createGame(){

        try{
//            ImageView smth = new ImageView();
//            File file = new File("C:\\Users\\onsmak\\IdeaProjects\\ija_project\\src\\ija\\ija2016\\project\\gui\\king.png");
//            Image image = new Image(file.toURI().toString());
//            smth.setImage(image);
//            smth.setFitHeight(145);
//            smth.setFitWidth(100);

            working_pane.add(this.cardImg,0,0);
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
