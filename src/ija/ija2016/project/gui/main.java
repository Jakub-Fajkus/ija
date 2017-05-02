package ija.ija2016.project.gui; /**
 * Created by onsmak on 02.05.2017.
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class main extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("gui.fxml"));
        primaryStage.setTitle("Madafaka Solitaire");
        primaryStage.setScene(new Scene(root, 970, 675));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
