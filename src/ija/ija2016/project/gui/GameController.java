package ija.ija2016.project.gui;

import ija.ija2016.project.game.GameFactory;
import ija.ija2016.project.game.GameInterface;
import ija.ija2016.project.game.GameObserverInterface;
import ija.ija2016.project.game.GameRuleValidator;
import ija.ija2016.project.game.command.MoveCommandInterface;
import ija.ija2016.project.game.command.MoveGameCommand;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.*;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class GameController implements Initializable, GameObserverInterface {

    final DragContext dragContext = new DragContext();
    @FXML
    public GridPane main_window;
    public Button btn__undo, btn__redo;
    // working stacks 1-7
    public GridPane working_pane;
    // top panel, target, wasting, drawing stacks
    public GridPane top_pane;
    CardPool cardPool;
    private WastingPackView wasting;
    private DrawingPackView drawing;
    // target stacks
    private TargetStackView target1, target2, target3, target4;
    // working stacks
    private WorkingStackView wstack1, wstack2, wstack3, wstack4, wstack5, wstack6, wstack7;
    private GameInterface game;

    private MoveCommandInterface actualMove;

    public GameController() {
        main_window = new GridPane();
        working_pane = new GridPane();

        this.game = (new GameFactory().createGame());
        this.cardPool = new CardPool(this.game.getAllCards());

        wstack1 = new WorkingStackView(this.game.getWorkingCardStacks()[0], this.game, this.cardPool, 0);
        wstack2 = new WorkingStackView(this.game.getWorkingCardStacks()[1], this.game, this.cardPool, 1);
        wstack3 = new WorkingStackView(this.game.getWorkingCardStacks()[2], this.game, this.cardPool, 2);
        wstack4 = new WorkingStackView(this.game.getWorkingCardStacks()[3], this.game, this.cardPool, 3);
        wstack5 = new WorkingStackView(this.game.getWorkingCardStacks()[4], this.game, this.cardPool, 4);
        wstack6 = new WorkingStackView(this.game.getWorkingCardStacks()[5], this.game, this.cardPool, 5);
        wstack7 = new WorkingStackView(this.game.getWorkingCardStacks()[6], this.game, this.cardPool, 6);

        target1 = new TargetStackView(this.game.getTargetPacks()[0], this.game, this.cardPool, 0);
        target2 = new TargetStackView(this.game.getTargetPacks()[1], this.game, this.cardPool, 1);
        target3 = new TargetStackView(this.game.getTargetPacks()[2], this.game, this.cardPool, 2);
        target4 = new TargetStackView(this.game.getTargetPacks()[3], this.game, this.cardPool, 3);

//        if (!this.game.move(this.game.getDrawingDeck(), this.game.getWastingDeck(), 1)) {
//            System.out.println("Can not move to waste");
//        } else {
//            System.out.println("Moved to waste");
//        }

        drawing = new DrawingPackView(this.game.getDrawingDeck(), this.game, this.cardPool);
        wasting = new WastingPackView(this.game.getWastingDeck(), this.game, this.cardPool);

        game.addObserver(this);

        game.addObserver(wstack1);
        game.addObserver(wstack2);
        game.addObserver(wstack3);
        game.addObserver(wstack4);
        game.addObserver(wstack5);
        game.addObserver(wstack6);
        game.addObserver(wstack7);

        game.addObserver(target1);
        game.addObserver(target2);
        game.addObserver(target3);
        game.addObserver(target4);

        game.addObserver(drawing);
        game.addObserver(wasting);
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

            this.makeNodeDraggable(this.wstack1.getChildren());
            this.makeNodeDraggable(this.wstack2.getChildren());
            this.makeNodeDraggable(this.wstack3.getChildren());
            this.makeNodeDraggable(this.wstack4.getChildren());
            this.makeNodeDraggable(this.wstack5.getChildren());
            this.makeNodeDraggable(this.wstack6.getChildren());
            this.makeNodeDraggable(this.wstack7.getChildren());

            this.wstack1.addEventHandler(DragEvent.DRAG_OVER, this::dragOver);
            this.wstack1.setOnMouseReleased(this::handleOnMouseReleased);

            this.wstack2.addEventHandler(DragEvent.DRAG_OVER, this::dragOver);
            this.wstack2.setOnMouseReleased(this::handleOnMouseReleased);

            this.wstack3.addEventHandler(DragEvent.DRAG_OVER, this::dragOver);
            this.wstack3.setOnMouseReleased(this::handleOnMouseReleased);

            this.wstack4.addEventHandler(DragEvent.DRAG_OVER, this::dragOver);
            this.wstack4.setOnMouseReleased(this::handleOnMouseReleased);

            this.wstack5.addEventHandler(DragEvent.DRAG_OVER, this::dragOver);
            this.wstack5.setOnMouseReleased(this::handleOnMouseReleased);

            this.wstack6.addEventHandler(DragEvent.DRAG_OVER, this::dragOver);
            this.wstack6.setOnMouseReleased(this::handleOnMouseReleased);

            this.wstack7.addEventHandler(DragEvent.DRAG_OVER, this::dragOver);
            this.wstack7.setOnMouseReleased(this::handleOnMouseReleased);

            top_pane.add(this.drawing, 0, 0);
            top_pane.add(this.wasting, 1, 0);

            this.makeNodeDraggable(this.drawing.getChildren());
            this.makeNodeDraggable(this.wasting.getChildren());

            top_pane.add(this.target1, 3, 0);
            top_pane.add(this.target2, 4, 0);
            top_pane.add(this.target3, 5, 0);
            top_pane.add(this.target4, 6, 0);

            this.makeNodeDraggable(this.target1.getChildren());
            this.makeNodeDraggable(this.target2.getChildren());
            this.makeNodeDraggable(this.target3.getChildren());
            this.makeNodeDraggable(this.target4.getChildren());
//
            this.target1.addEventHandler(DragEvent.DRAG_OVER, this::dragOver);
            this.target1.setOnMouseReleased(this::handleOnMouseReleased);

            this.target2.addEventHandler(DragEvent.DRAG_OVER, this::dragOver);
            this.target2.setOnMouseReleased(this::handleOnMouseReleased);

            this.target3.addEventHandler(DragEvent.DRAG_OVER, this::dragOver);
            this.target3.setOnMouseReleased(this::handleOnMouseReleased);

            this.target4.addEventHandler(DragEvent.DRAG_OVER, this::dragOver);
            this.target4.setOnMouseReleased(this::handleOnMouseReleased);

            this.drawing.setOnMousePressed(this::getCardFromDrawingPack);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("fail create stack");
        }

    }

    /**
     * Get card from wasting deck to drawing on click event
     */
    private void getCardFromDrawingPack(MouseEvent e) {
        System.out.println("in da click");
        this.actualMove = null;

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
    }


    private void makeNodeDraggable(List<Node> cardView) {
        for (Node node : cardView) {
            this.makeNodeDraggable(node);
        }

    }

    private void makeNodeDraggable(Node node) {
        node.addEventHandler(MouseEvent.DRAG_DETECTED, this::dragDetected);
        node.addEventHandler(DragEvent.DRAG_OVER, this::dragOver);

        node.setOnMousePressed(this::handleOnMousePressed);
        node.setOnMouseDragged(this::handleOnMouseDragged);
        node.setOnMouseReleased(this::handleOnMouseReleased);
        node.setOnDragDone(this::handleOnDragDone);
    }


    public void handleOnMousePressed(MouseEvent event) {
        System.out.println("handleOnMousePressed");
        CardView clickedCard = (CardView) event.getSource();
        dragContext.x = event.getSceneX();
        dragContext.y = event.getSceneY() - clickedCard.getOffset();
    }

    public void handleOnMouseDragged(MouseEvent event) {
        System.out.println("handleOnMouseDragged");
        Node node = (Node) event.getSource();

        double offsetX = event.getSceneX() - dragContext.x;
        double offsetY = event.getSceneY() - dragContext.y;

        node.getParent().toFront();
        node.setTranslateX(offsetX);
        node.setTranslateY(offsetY);
    }

    public void handleOnDragDone(DragEvent event) {

        Node node = (Node) event.getSource();

        double offsetX = event.getSceneX() - dragContext.x;
        double offsetY = event.getSceneY() - dragContext.y;

        node.getParent().toFront();
        node.setTranslateX(offsetX);
        node.setTranslateY(offsetY);
    }

    public void handleOnMouseReleased(MouseEvent event) {
        System.out.println("handleOnMouseReleased on stack");

        if (event.getSource() instanceof CardView) {
            CardView cardView = (CardView) event.getSource();

            cardView.setTranslateX(0);
            cardView.setTranslateY(0 + cardView.getOffset());
        } else {
            System.out.println("handleOnMouseReleased,, not a CardView, continuing");
            return;
        }

        if (this.actualMove == null || this.actualMove.getSource() == null || this.actualMove.getDestination() == null) {
            this.actualMove = new MoveGameCommand(null, null, 1);
            System.out.println("Something is misssing in the command, leaving" + this.actualMove + this.actualMove.getSource() + this.actualMove.getDestination());
            return;
        }

        if (this.actualMove.getSource() == this.actualMove.getDestination()) {
            this.actualMove = new MoveGameCommand(null, null, 1);
            System.out.println("Do not move source=dest");
            return;
        }

        if (this.game.move(this.actualMove)) {
            System.out.println("MOVED");
        } else {
            System.out.println("NOT MOVED");

        }
    }

    public void dragOver(DragEvent e) {
        if (e.getSource() instanceof CardView) {
            CardView cardView = (CardView) e.getSource();
            GuiStackPane stack = cardView.getContainingElement();
            System.out.println("Over stack drag: " + stack.toString() + "\n");
            this.actualMove.setDestination(stack.getPack());
        } else if (e.getSource() instanceof TargetStackView) {
            GuiStackPane stack = (GuiStackPane) e.getSource();
            System.out.println("Over GuiStackPlane drag: " + stack.toString() + "\n");
            this.actualMove.setDestination(stack.getPack());
        } else {
            System.out.println("Drag over: " + e.getClass() + " on object " + e.getSource());
            GuiStackPane stack = (GuiStackPane) e.getSource();
            this.actualMove.setDestination(stack.getPack());
        }
    }

//    public void dragOver(DragEvent e) {
//        GuiStackPane stack;
//        if (e.getSource() instanceof CardView) {
//            CardView cardView = (CardView) e.getSource();
//            stack = cardView.getContainingElement();
//
//            System.out.println("Over cardView drag: " + stack.toString() + "\n");
//        } else if (e.getSource() instanceof TargetStackView || e.getSource() instanceof WorkingStackView) {
//            stack = (GuiStackPane)e.getSource();
//            System.out.println("Over GuiStackPlane drag: " + stack.toString() + "\n");
//        } else {
//            System.out.println("Drag over: " + e.getClass() + " on object " + e.getSource());
//            return;
//        }
//
//        this.actualMove.setDestination(stack.getPack());
//    }


    public void dragDetected(MouseEvent event) {

        CardView cardView = (CardView) event.getSource();
        System.out.println("Draged from stack: " + cardView.getContainingElement().toString() + "\n");
//        System.out.println("Count of cards in stack: "+cardView.getContainingElement().getChildren().size()+"\n");
//        System.out.println("Offset of dragged card: "+this.getOffset()+"\n");
//        System.out.println("Poradi karty: "+((this.getOffset()/20)+1.0)+"\n");

        int countOfCards;

        GameRuleValidator validator = new GameRuleValidator(this.game);
        if (cardView.getContainingElement().getPack() == this.game.getWastingDeck() || validator.isTargetStack(cardView.getContainingElement().getPack())) {
            countOfCards = 1;
        } else {
            countOfCards = (int) (cardView.getContainingElement().getChildren().size() - ((cardView.getOffset() / 20)));
        }

        System.out.println("Pocet karet: " + countOfCards + "\n");

        if (!cardView.isTurnedFaceUp()) {
            System.out.println("The card is not facing up!" + cardView.getCard().toString() + cardView.getCard().hashCode());
            return;
        }

        Dragboard dragboard = cardView.startDragAndDrop(TransferMode.MOVE);
        ClipboardContent content = new ClipboardContent();
        content.putString("card");
        dragboard.setContent(content);
        event.consume();

        this.actualMove = new MoveGameCommand(cardView.getContainingElement().getPack(), null, countOfCards);
    }

    @Override
    public void updateOnGameChange() {
        System.out.println("updating the game state!");
        this.cardPool.updateCards(this.game.getAllCards());
    }
}
