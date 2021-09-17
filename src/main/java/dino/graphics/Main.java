package dino.graphics;

import java.io.IOException;

import dino.Dino;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Dino using FXML.
 */
public class Main extends Application {

    private Dino dino = new Dino();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Dino");
            stage.getIcons().add(new Image(ClassLoader.getSystemResourceAsStream("images/Dinner.jpg")));
            fxmlLoader.<MainWindow>getController().setDino(dino);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
