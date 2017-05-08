/*
 * IJA2016 Solitaire
 * Jakub Fajkus & David Czernin
 */

package ija.ija2016.project.gui;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainWindow extends Application implements Initializable {

    private static Stage primaryStageCover;
    @FXML
    public GridPane main_window;
    @FXML
    public MenuItem addGameMenu;
    public Menu closeGameMenu;
    private MenuItem game1;
    private MenuItem game2;
    private MenuItem game3;
    private MenuItem game4;
    private int gameCounter = 0;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        primaryStageCover = primaryStage;

        try {
            Parent root = FXMLLoader.load(this.getClass().getResource("MainWindow.fxml"));
            primaryStage.setTitle("Solitaire");
            primaryStage.setScene(new Scene(root, 720, 450));
            primaryStage.setMinHeight(450);
            primaryStage.setMinWidth(720);
            primaryStage.setResizable(true);
            primaryStage.show();
        } catch (Exception error) {
            error.printStackTrace();
            System.exit(1);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("Game.fxml"));
            Parent root = fxmlLoader.load();
            GameController controller = fxmlLoader.getController();

            this.gameCounter++;
            this.main_window.add(root, 0, 0);
            this.game1 = new MenuItem("Hra 1");
            this.closeGameMenu.getItems().addAll(this.game1);

            this.addGameMenu.setOnAction(e -> MainWindow.this.gamesGridHandler());
            this.game1.setOnAction(e -> MainWindow.this.closeGameHandler(1));

        } catch (Exception error) {
            System.exit(1);
        }
    }

    private void gamesGridHandler() {
        primaryStageCover.setMaximized(true);

        if (this.gameCounter == 4) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Hrací plochy jsou plné");
            alert.setHeaderText("Již nelze vytvořit další hru, lze hrát pouze 4 naráz.");

            alert.showAndWait();
            return;
        }

        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("Game.fxml"));
        Parent gameRoot = null;
        try {
            gameRoot = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        GameController controller = fxmlLoader.getController();

        RowConstraints row1 = this.main_window.getRowConstraints().get(0);
        RowConstraints row2 = this.main_window.getRowConstraints().get(1);

        ColumnConstraints column1 = this.main_window.getColumnConstraints().get(0);
        ColumnConstraints column2 = this.main_window.getColumnConstraints().get(1);

        row1.setPercentHeight(50);
        row2.setPercentHeight(50);

        column1.setPercentWidth(50);
        column2.setPercentWidth(50);

        if (this.gameCounter == 4) {
            return;
        } else if (this.gameCounter == 0) {
            this.main_window.add(gameRoot, 0, 0);
            this.game1 = new MenuItem("Hra 1");
            this.closeGameMenu.getItems().addAll(this.game1);
            this.game1.setOnAction(e -> MainWindow.this.closeGameHandler(1));
        } else if (this.gameCounter == 1) {
            this.main_window.add(gameRoot, 1, 0);
            this.game2 = new MenuItem("Hra 2");
            this.closeGameMenu.getItems().addAll(this.game2);
            this.game2.setOnAction(e -> MainWindow.this.closeGameHandler(2));
        } else if (this.gameCounter == 2) {
            this.main_window.add(gameRoot, 0, 1);
            this.game3 = new MenuItem("Hra 3");
            this.closeGameMenu.getItems().addAll(this.game3);
            this.game3.setOnAction(e -> MainWindow.this.closeGameHandler(3));
        } else if ((this.gameCounter == 3)) {
            this.main_window.add(gameRoot, 1, 1);
            this.game4 = new MenuItem("Hra 4");
            this.closeGameMenu.getItems().addAll(this.game4);
            this.game4.setOnAction(e -> MainWindow.this.closeGameHandler(4));
        }
        this.gameCounter++;
    }

    private void closeGameHandler(int id) {
        if (id == 1) {
            Node res = this.getNodeByRowColumnIndex(0, 0, this.main_window);
            this.main_window.getChildren().removeAll(res);

            Node g2 = this.getNodeByRowColumnIndex(0, 1, this.main_window);
            Node g3 = this.getNodeByRowColumnIndex(1, 0, this.main_window);
            Node g4 = this.getNodeByRowColumnIndex(1, 1, this.main_window);

            if (g4 != null) {
                this.main_window.getChildren().removeAll(g4);
                this.main_window.add(g4, 0, 1);
            }
            if (g3 != null) {
                this.main_window.getChildren().removeAll(g3);
                this.main_window.add(g3, 1, 0);
            }
            if (g2 != null) {
                this.main_window.getChildren().removeAll(g2);
                this.main_window.add(g2, 0, 0);
            }

        } else if (id == 2) {
            Node res = this.getNodeByRowColumnIndex(0, 1, this.main_window);
            this.main_window.getChildren().removeAll(res);

            Node g3 = this.getNodeByRowColumnIndex(1, 0, this.main_window);
            if (g3 != null) {
                this.main_window.getChildren().removeAll(g3);
                this.main_window.add(g3, 1, 0);
            }

            Node g4 = this.getNodeByRowColumnIndex(1, 1, this.main_window);
            if (g4 != null) {
                this.main_window.getChildren().removeAll(g4);
                this.main_window.add(g4, 0, 1);
            }

        } else if (id == 3) {
            Node res = this.getNodeByRowColumnIndex(1, 0, this.main_window);
            this.main_window.getChildren().removeAll(res);
            Node g4 = this.getNodeByRowColumnIndex(1, 1, this.main_window);
            if (g4 != null) {
                this.main_window.getChildren().removeAll(g4);
                this.main_window.add(g4, 0, 1);
            }
        } else if (id == 4) {
            Node res = this.getNodeByRowColumnIndex(1, 1, this.main_window);
            this.main_window.getChildren().removeAll(res);
        }
        this.gameCounter--;
        this.removeMenuGames();
        if (this.gameCounter == 1) {
            primaryStageCover.setMaximized(false);
        }
    }

    private Node getNodeByRowColumnIndex(final int row, final int column, GridPane gridPane) {
        Node result = null;
        ObservableList<Node> childrens = gridPane.getChildren();

        for (Node node : childrens) {
            if (GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == column) {
                result = node;
                break;
            }
        }

        return result;
    }

    private void removeMenuGames() {
        if (this.gameCounter == 0) {
            this.closeGameMenu.getItems().removeAll(this.game1);
            this.closeGameMenu.getItems().removeAll(this.game2);
            this.closeGameMenu.getItems().removeAll(this.game3);
            this.closeGameMenu.getItems().removeAll(this.game4);
        }
        if (this.gameCounter == 1) {
            this.closeGameMenu.getItems().removeAll(this.game2);
            this.closeGameMenu.getItems().removeAll(this.game3);
            this.closeGameMenu.getItems().removeAll(this.game4);
        } else if (this.gameCounter == 2) {
            this.closeGameMenu.getItems().removeAll(this.game3);
            this.closeGameMenu.getItems().removeAll(this.game4);
        } else if (this.gameCounter == 3) {
            this.closeGameMenu.getItems().removeAll(this.game4);
        }
    }


}
