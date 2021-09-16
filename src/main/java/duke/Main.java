package duke;

import java.io.IOException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 * Taken / adapted from Seedu JavaFX Tutorial Part4
 */
public class Main extends Application {

    private Duke duke = new Duke();
    private Image logo = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Starts and loads the applicaion.
     * Trigger duke to initialise all classes.
     * Initialise and shows all GUI element to the given stage.
     *
     * @param stage The stage where the GUI should be shown.
     */
    @Override
    public void start(Stage stage) {
        try {
            duke.run();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.setTitle("DUKE");
            stage.getIcons().add(logo);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Exits the application
     */
    public static void exit() {
        Platform.exit();
    }
}
