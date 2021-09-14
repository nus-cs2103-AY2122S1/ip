package catobot;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Catobot using FXML.
 */
public class Main extends Application {

    private final Catobot catobot = new Catobot("./data/Catobot.txt");

    /**
     * Starts the scene.
     *
     * @param stage The stage that has this scene.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);

            // fix for font
            scene.getRoot().setStyle("-fx-font-family: 'Arial'");
            stage.setScene(scene);
            stage.setTitle("Catobot");
            fxmlLoader.<MainWindow>getController().setCatobot(catobot);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
