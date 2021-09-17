package Wonderland;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Wonderland using FXML.
 */
public class Main extends Application {

    private Wonderland wonderland = new Wonderland();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle("Wonderland");
            stage.setScene(scene);
            stage.getIcons().add(new Image("/images/rabbit.png"));
            fxmlLoader.<MainWindow>getController().setDuke(wonderland);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
