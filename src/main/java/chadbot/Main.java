package chadbot;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Chad using FXML.
 */
public class Main extends Application {

    private static Stage stage;
    private Chad chad = new Chad("chadbot.txt");
    private Image chadImage = new Image(this.getClass().getResourceAsStream("/images/chad.png"));

    @Override
    public void start(Stage stage) {
        try {
            this.stage = stage;
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setChad(chad);
            stage.setTitle("Chadbot");
            stage.getIcons().add(chadImage);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void exit() {
        stage.close();
    }
}
