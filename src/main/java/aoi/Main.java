package aoi;

import java.io.IOException;

import aoi.controllers.MainWindow;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for aoi.Aoi using FXML.
 */
public class Main extends Application {

    private Aoi aoi = new Aoi("data/tasks.txt");

    @Override
    public void start(Stage stage) {
        MainWindow app = new MainWindow(aoi);
        Scene scene = new Scene(app);
        scene.getStylesheets().add("/view/styles.css");
        stage.getIcons().add(new Image(this.getClass().getResourceAsStream("/images/reminder.png")));
        stage.setTitle("Aoi Todo Bot");
        stage.setScene(scene);
        stage.show();
    }
}
