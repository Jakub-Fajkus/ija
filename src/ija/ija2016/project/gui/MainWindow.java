/**
 * Created by onsmak on 02.05.2017.
 */


package ija.ija2016.project.gui;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainWindow extends Application implements Initializable {

    @FXML public GridPane main_window;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("MainWindow.fxml"));
            primaryStage.setTitle("Madafaka Solitaire");
            primaryStage.setScene(new Scene(root, 825, 600));
            primaryStage.setMinHeight(600);
            primaryStage.setMinWidth(825);
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch (Exception error){
            System.out.print(error.toString());
            System.exit(1);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Game.fxml"));
            Parent root = fxmlLoader.load();
            GameController controller = fxmlLoader.getController();

            main_window.add(root, 0, 0);

        }  catch (Exception error){
            System.out.print(error.toString());
            System.exit(1);
        }
    }


}
