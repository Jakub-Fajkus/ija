package ija.ija2016.project.gui;

import ija.ija2016.project.game.*;
import ija.ija2016.project.model.cards.Card;
import ija.ija2016.project.model.cards.CardInterface;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.io.Serializable;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.control.Button;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;


public class GameController implements Initializable{

    @FXML
    public GridPane main_window;
    public Button btn__undo, btn__redo;

    private GameObserverInterface gameObserve;

    // working stacks 1-7
    public GridPane working_pane;

    // top panel, target, wasting, drawing stacks
    public GridPane top_pane;

    private WastingPackView wasting;
    private DrawingPackView drawing;

    // target stacks
    private TargetStackView target1, target2, target3, target4;

    // working stacks
    private WorkingStackView wstack1, wstack2, wstack3, wstack4, wstack5, wstack6, wstack7;


    private GameInterface game;

    public GameController() {
        main_window = new GridPane();
        working_pane = new GridPane();


        this.game = (new GameFactory().createGame());
        wstack1 = new WorkingStackView(this.game.getWorkingCardStacks()[0]);
        wstack2 = new WorkingStackView(this.game.getWorkingCardStacks()[1]);
        wstack3 = new WorkingStackView(this.game.getWorkingCardStacks()[2]);
        wstack4 = new WorkingStackView(this.game.getWorkingCardStacks()[3]);
        wstack5 = new WorkingStackView(this.game.getWorkingCardStacks()[4]);
        wstack6 = new WorkingStackView(this.game.getWorkingCardStacks()[5]);
        wstack7 = new WorkingStackView(this.game.getWorkingCardStacks()[6]);

        target1 = new TargetStackView(this.game.getTargetPacks()[0]);
        target2 = new TargetStackView(this.game.getTargetPacks()[1]);
        target3 = new TargetStackView(this.game.getTargetPacks()[2]);
        target4 = new TargetStackView(this.game.getTargetPacks()[3]);

        drawing = new DrawingPackView(this.game.getDrawingDeck());
        wasting = new WastingPackView(this.game.getWastingDeck());


        game.addObserver(this.gameObserve);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        this.createGame();
    }

    public void createGame() {

        try {
            working_pane.add(this.wstack1, 0, 0);
            working_pane.add(this.wstack2, 1, 0);
            working_pane.add(this.wstack3, 2, 0);
            working_pane.add(this.wstack4, 3, 0);
            working_pane.add(this.wstack5, 4, 0);
            working_pane.add(this.wstack6, 5, 0);
            working_pane.add(this.wstack7, 6, 0);

            top_pane.add(this.drawing, 0, 0);
            top_pane.add(this.wasting, 1, 0);

            top_pane.add(this.target1, 3, 0);
            top_pane.add(this.target2, 4, 0);
            top_pane.add(this.target3, 5, 0);
            top_pane.add(this.target4, 6, 0);

            this.drawing.setOnMousePressed(this::getCardFromDrawingPack);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.print("fail create stack");
        }

    }

    /**
     * Get card from wasting deck to drawing on click event
     */
    private void getCardFromDrawingPack(MouseEvent e) {
        System.out.print("in da click");
        if (!this.game.getDrawingDeck().isEmpty()) {
            try {
                System.out.print("in da move");
                this.game.getDrawingDeck().put(new Card(CardInterface.Color.CLUBS, 3,true));
                this.game.move(this.game.getWastingDeck(),this.game.getDrawingDeck(), 1);
                //TODO update, redraw
            } catch (Exception er) {
                er.printStackTrace();
            }
        }
    }

}
