package ija.ija2016.project.gui;

import ija.ija2016.project.game.GameFactory;
import ija.ija2016.project.game.GameInterface;
import ija.ija2016.project.game.GameObserverInterface;
import ija.ija2016.project.game.GameRuleValidator;
import ija.ija2016.project.game.command.MoveCommandInterface;
import ija.ija2016.project.game.command.MoveGameCommand;
import ija.ija2016.project.game.persistence.LoadStateException;
import ija.ija2016.project.game.persistence.PersistStateException;
import ija.ija2016.project.model.cards.CardDeckInterface;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class GameController implements Initializable, GameObserverInterface {

    //    @FXML
//    public GridPane main_window;
    @FXML
    public MenuItem saveMenu, loadMenu, restartMenu, newGameMenu, tipMenu;
    @FXML
    public Button btn__undo;
    // working stacks 1-7
    public GridPane working_pane;
    // top panel, target, wasting, drawing stacks
    public GridPane top_pane;
    private CardPool cardPool;
    private ArrayList<MoveCommandInterface> tips;
    private WastingPackView wasting;
    private DrawingPackView drawing;
    // target stacks
    private TargetStackView target1, target2, target3, target4;
    private ArrayList<TargetStackView> targetStacks;
    // working stacks
    private WorkingStackView wstack1, wstack2, wstack3, wstack4, wstack5, wstack6, wstack7;
    private ArrayList<WorkingStackView> workingStacks;
    private ArrayList<GuiStackPane> allCardPanes;
    private GameInterface game;
    private MoveCommandInterface actualMove;

    public GameController() {
//        this.main_window = new GridPane();
        this.working_pane = new GridPane();

        this.game = (new GameFactory().createGame());
        this.cardPool = new CardPool(this.game.getAllCards());

        this.wstack1 = new WorkingStackView(this.game.getWorkingCardStacks()[0], this.game, this.cardPool, 0);
        this.wstack2 = new WorkingStackView(this.game.getWorkingCardStacks()[1], this.game, this.cardPool, 1);
        this.wstack3 = new WorkingStackView(this.game.getWorkingCardStacks()[2], this.game, this.cardPool, 2);
        this.wstack4 = new WorkingStackView(this.game.getWorkingCardStacks()[3], this.game, this.cardPool, 3);
        this.wstack5 = new WorkingStackView(this.game.getWorkingCardStacks()[4], this.game, this.cardPool, 4);
        this.wstack6 = new WorkingStackView(this.game.getWorkingCardStacks()[5], this.game, this.cardPool, 5);
        this.wstack7 = new WorkingStackView(this.game.getWorkingCardStacks()[6], this.game, this.cardPool, 6);

        this.target1 = new TargetStackView(this.game.getTargetPacks()[0], this.game, this.cardPool, 0);
        this.target2 = new TargetStackView(this.game.getTargetPacks()[1], this.game, this.cardPool, 1);
        this.target3 = new TargetStackView(this.game.getTargetPacks()[2], this.game, this.cardPool, 2);
        this.target4 = new TargetStackView(this.game.getTargetPacks()[3], this.game, this.cardPool, 3);

        this.drawing = new DrawingPackView(this.game.getDrawingDeck(), this.game, this.cardPool);
        this.wasting = new WastingPackView(this.game.getWastingDeck(), this.game, this.cardPool);

        this.workingStacks = new ArrayList<>();
        this.workingStacks.add(this.wstack1);
        this.workingStacks.add(this.wstack2);
        this.workingStacks.add(this.wstack3);
        this.workingStacks.add(this.wstack4);
        this.workingStacks.add(this.wstack5);
        this.workingStacks.add(this.wstack6);
        this.workingStacks.add(this.wstack7);

        this.targetStacks = new ArrayList<>();
        this.targetStacks.add(this.target1);
        this.targetStacks.add(this.target2);
        this.targetStacks.add(this.target3);
        this.targetStacks.add(this.target4);

        this.allCardPanes = new ArrayList<>();
        this.allCardPanes.addAll(this.workingStacks);
        this.allCardPanes.addAll(this.targetStacks);
        this.allCardPanes.add(this.drawing);
        this.allCardPanes.add(this.wasting);

        this.game.addObserver(this);

        this.game.addObserver(this.wstack1);
        this.game.addObserver(this.wstack2);
        this.game.addObserver(this.wstack3);
        this.game.addObserver(this.wstack4);
        this.game.addObserver(this.wstack5);
        this.game.addObserver(this.wstack6);
        this.game.addObserver(this.wstack7);

        this.game.addObserver(this.target1);
        this.game.addObserver(this.target2);
        this.game.addObserver(this.target3);
        this.game.addObserver(this.target4);

        this.game.addObserver(this.drawing);
        this.game.addObserver(this.wasting);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        this.createGame();
    }

    private void createGame() {
        try {
            this.working_pane.add(this.wstack1, 0, 0);
            this.working_pane.add(this.wstack2, 1, 0);
            this.working_pane.add(this.wstack3, 2, 0);
            this.working_pane.add(this.wstack4, 3, 0);
            this.working_pane.add(this.wstack5, 4, 0);
            this.working_pane.add(this.wstack6, 5, 0);
            this.working_pane.add(this.wstack7, 6, 0);

            this.wstack1.setOnMousePressed(this::onStackMousePressed);
            this.wstack2.setOnMousePressed(this::onStackMousePressed);
            this.wstack3.setOnMousePressed(this::onStackMousePressed);
            this.wstack4.setOnMousePressed(this::onStackMousePressed);
            this.wstack5.setOnMousePressed(this::onStackMousePressed);
            this.wstack6.setOnMousePressed(this::onStackMousePressed);
            this.wstack7.setOnMousePressed(this::onStackMousePressed);

            for (Node node : this.wstack1.getChildren()) {
                node.setOnMousePressed(this::onStackMousePressed);
            }

            this.wstack1.setOnMousePressed(this::onStackMousePressed);
            this.wstack2.setOnMousePressed(this::onStackMousePressed);
            this.wstack3.setOnMousePressed(this::onStackMousePressed);
            this.wstack4.setOnMousePressed(this::onStackMousePressed);
            this.wstack5.setOnMousePressed(this::onStackMousePressed);
            this.wstack6.setOnMousePressed(this::onStackMousePressed);
            this.wstack7.setOnMousePressed(this::onStackMousePressed);

            this.top_pane.add(this.drawing, 0, 0);
            this.top_pane.add(this.wasting, 1, 0);

            this.wasting.setOnMousePressed(this::onStackMousePressed);

            this.top_pane.add(this.target1, 3, 0);
            this.top_pane.add(this.target2, 4, 0);
            this.top_pane.add(this.target3, 5, 0);
            this.top_pane.add(this.target4, 6, 0);

            this.target1.setOnMousePressed(this::onStackMousePressed);
            this.target2.setOnMousePressed(this::onStackMousePressed);
            this.target3.setOnMousePressed(this::onStackMousePressed);
            this.target4.setOnMousePressed(this::onStackMousePressed);

            this.drawing.setOnMousePressed(e1 -> this.getCardFromDrawingPack());
            this.btn__undo.setOnMouseClicked(mouseEvent -> this.undoButtonClicked());

            this.saveMenu.setOnAction(e -> GameController.this.saveButtonClicked());

            this.loadMenu.setOnAction(e -> GameController.this.loadButtonClicked());

            this.restartMenu.setOnAction(e -> GameController.this.restartButtonClicked());

            this.newGameMenu.setOnAction(e -> GameController.this.newGameButtonClicked());

            this.tipMenu.setOnAction(e -> GameController.this.tipButtonClicked());

            this.actualMove = new MoveGameCommand(null, null, 1);
            this.setMouseEventsOnAllCardViews();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("fail create stack");
        }
    }

    private void tipButtonClicked() {

        if (this.tips == null) {
            System.out.println("Tips == null, trying to get the tips!");
            this.tips = this.game.tip();
            if (this.tips.isEmpty()) {
                System.out.println("No tips found empty!");
            }
        }

        //get the first tip
        if (this.tips.isEmpty()) {
            System.out.println("No tips available");

            return;
        } else {
            this.clearSelectEffects();
        }

        MoveCommandInterface move = this.tips.get(0);
        this.tips.remove(0);

        try {
            //find the source gui element
            GuiStackPane source = this.findViewContainingDeck(move.getSource());
            GuiStackPane destination = this.findViewContainingDeck(move.getDestination());

            System.out.println("Show tip from " + source.toString() + " to " + destination.toString() + " with count " + move.getCount());

            if (source == this.drawing && destination == this.wasting) {
                source.setEffect(new DropShadow(BlurType.ONE_PASS_BOX, new Color(1, 0.9882, 0.149, 0.8), 20, 10, 0, 0));
                return;
            }

            if (destination instanceof TargetStackView || destination instanceof DrawingPackView) {
                destination.setEffect(new DropShadow(BlurType.ONE_PASS_BOX, new Color(1, 0.9882, 0.149, 0.8), 20, 10, 0, 0));

                if (source instanceof TargetStackView || source instanceof DrawingPackView) {
                    destination.setEffect(new DropShadow(BlurType.ONE_PASS_BOX, new Color(1, 0.9882, 0.149, 0.8), 20, 10, 0, 0));
                }
            } else {
                if (!source.getChildren().isEmpty() && !destination.getChildren().isEmpty()) {
                    CardView destinationCardView = (CardView) destination.getChildren().get(destination.getChildren().size() - 1);
                    destinationCardView.setTipShadow();
                }
            }

            CardView sourceCardView = (CardView) source.getChildren().get(source.getChildren().size() - 1);
            sourceCardView.setTipShadow();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private GuiStackPane findViewContainingDeck(CardDeckInterface deck) throws Exception {
        for (GuiStackPane pane : this.allCardPanes) {
            if (pane.getPack() == deck) {
                return pane;
            }
        }

        throw new Exception("NO pane found for deck " + deck);
    }

    private void newGameButtonClicked() {
        this.game.initializeWithCards((new GameFactory()).getNewCardDeck());
    }

    private void setMouseEventsOnAllCardViews() {
        this.setMouseEventOnCardViewsStackPane(this.wasting);
        this.setMouseEventOnCardViewsStackPane(this.drawing);

        this.setMouseEventOnCardViewsStackPane(this.target1);
        this.setMouseEventOnCardViewsStackPane(this.target2);
        this.setMouseEventOnCardViewsStackPane(this.target3);
        this.setMouseEventOnCardViewsStackPane(this.target4);

        this.setMouseEventOnCardViewsStackPane(this.wstack1);
        this.setMouseEventOnCardViewsStackPane(this.wstack2);
        this.setMouseEventOnCardViewsStackPane(this.wstack3);
        this.setMouseEventOnCardViewsStackPane(this.wstack4);
        this.setMouseEventOnCardViewsStackPane(this.wstack5);
        this.setMouseEventOnCardViewsStackPane(this.wstack6);
        this.setMouseEventOnCardViewsStackPane(this.wstack7);
    }

    private void setMouseEventOnCardViewsStackPane(GuiStackPane node) {
        for (Node child : node.getChildren()) {
            child.setOnMousePressed(this::onCardMousePressed);
        }
    }

    private void onCardMousePressed(MouseEvent mouseEvent) {
        System.out.println("onCardMousePressed on card" + mouseEvent.getSource());

        CardView cardView = (CardView) mouseEvent.getSource();

        if (this.actualMove.getSource() == null) {
            int countOfCards;

            //when there is no source, there should be no destination
            this.actualMove.setDestination(null);

            GameRuleValidator validator = new GameRuleValidator(this.game);
            if (cardView.getContainingElement().getPack() == this.game.getWastingDeck() || validator.isTargetStack(cardView.getContainingElement().getPack())) {
                countOfCards = 1;
            } else {
                //todo: better formula!? count the position in the inner array?
                countOfCards = (int) (cardView.getContainingElement().getChildren().size() - ((cardView.getOffset() / 15)));
            }

            System.out.println("Pocet karet: " + countOfCards + "\n");

            if (!cardView.isTurnedFaceUp()) {
                System.out.println("The card is not facing up!" + cardView.getCard().toString() + cardView.getCard().hashCode());
                return;
            }

            cardView.setSelectedShadow();

            this.actualMove.setSource(cardView.getContainingElement().getPack());
            this.actualMove.setCount(countOfCards);
            mouseEvent.consume();
        } else if (cardView.getContainingElement().getPack() == this.actualMove.getSource()) {
            //the user clicked on the same card again
            this.actualMove.setSource(null);
            cardView.removeShadow();
            mouseEvent.consume();
        }
    }

    private void onStackMousePressed(MouseEvent e) {
        System.out.println("onStackMousePressed on " + e.getSource());

        GuiStackPane pane = (GuiStackPane) e.getSource();

        if (this.actualMove.getDestination() == null) {
            this.actualMove.setDestination(pane.getPack());

            if (this.actualMove == null || this.actualMove.getSource() == null || this.actualMove.getDestination() == null) {
//                this.actualMove = new MoveGameCommand(null, null, 1);
                System.out.println("Something is misssing in the command, leaving" + this.actualMove + this.actualMove.getSource() + this.actualMove.getDestination());
                return;
            }

            if (this.actualMove.getSource() == this.actualMove.getDestination()) {
//                this.actualMove = new MoveGameCommand(null, null, 1);
                this.actualMove.setDestination(null);
                System.out.println("Do not move source=dest");
                return;
            }

            System.out.println("Moving from: " + this.actualMove.getSource());
            System.out.println("Moving to: " + e.getSource());

            if (this.game.move(this.actualMove)) {
                System.out.println("MOVED");

                if (this.game.isFinished()) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Konec hry");
                    alert.setHeaderText("Vyhráli jste, gratulujeme");

                    alert.showAndWait();
                }
            } else {
                System.out.println("NOT MOVED");
            }

            this.actualMove = new MoveGameCommand(null, null, 1);
        }
    }

    private void saveButtonClicked() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save game");

        File file = fileChooser.showSaveDialog(this.working_pane.getScene().getWindow());
        if (file != null) {
            try {
                this.game.persistState(file.getPath());
                System.out.println("SUCCESSfully saved");
            } catch (PersistStateException e) {
                e.printStackTrace();
                System.out.println("Can not save\n");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Ukládání hry");
                alert.setHeaderText("Hru nelze uložit.");
                alert.showAndWait();
            }
        }
    }

    private void loadButtonClicked() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Load game");

        File file = fileChooser.showOpenDialog(this.working_pane.getScene().getWindow());
        if (file != null) {
            try {
                this.game.loadState(file.getPath());
            } catch (LoadStateException e) {
                e.printStackTrace();
                System.out.println("Can not load game\n");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Načtení uložené hry");
                alert.setHeaderText("Hru nelze načíst.");
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            }
        }
    }

    private void restartButtonClicked() {
        System.out.println("RESTARTING THE GAME" + this.game.restartGame());
    }

    private void undoButtonClicked() {
        this.game.undo();
        System.out.println("UNDO DONE");
    }

    /**
     * Get card from wasting deck to drawing on click event
     */
    private void getCardFromDrawingPack() {
        System.out.println("in da click");

        if (!this.game.getDrawingDeck().isEmpty()) {
            try {
                System.out.println("in da move");
                if (this.game.move(this.game.getDrawingDeck(), this.game.getWastingDeck(), 1)) {
                    System.out.println("moved!");
                } else {
                    System.out.println("did not move!");
                }
            } catch (Exception er) {
                er.printStackTrace();
            }
        } else {
            if (this.game.move(this.game.getWastingDeck(), this.game.getDrawingDeck(), 0)) {
                System.out.println("transfered!");
            } else {
                System.out.println("did not transfered");
            }
        }

        this.actualMove = new MoveGameCommand(null, null, 1);
    }

    @Override
    public void updateOnGameChange() {
        System.out.println("GameController:updateOnGameChange updating the game state!");
        this.cardPool.updateCards(this.game.getAllCards());
        this.actualMove = new MoveGameCommand(null, null, 1);
        this.tips = null;

        this.clearSelectEffects();
    }

    private void clearSelectEffects() {
        for (WorkingStackView working : this.workingStacks) {
            working.setEffect(null);
            for (Node child : working.getChildren()) {
                child.setEffect(null);
            }
        }

        for (TargetStackView target : this.targetStacks) {
            target.setEffect(null);
            for (Node child : target.getChildren()) {
                child.setEffect(null);
            }
        }

        this.drawing.setEffect(null);
        this.wasting.setEffect(null);
    }
}
