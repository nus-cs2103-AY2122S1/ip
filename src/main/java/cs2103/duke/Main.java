package cs2103.duke;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import cs2103.duke.controllers.MainWindow;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private final Duke duke = new Duke();

    public Main() {
    }

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            stage.setTitle("Duke");
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            scene.getRoot().setStyle("-fx-font-family: 'Courier New'");
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}