package aoi;

import java.io.IOException;

import aoi.controller.MainWindow;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.image.Image;

/**
 * A GUI for aoi.Aoi using FXML.
 */
public class Main extends Application {

    private Aoi aoi = new Aoi("data/tasks.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.getIcons().add(new Image(this.getClass().getResourceAsStream("/images/reminder.png")));
            stage.setTitle("Aoi Todo Bot");
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setAoi(aoi);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
