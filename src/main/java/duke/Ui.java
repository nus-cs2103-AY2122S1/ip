package duke;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A class to represent the user interface of Duke.
 */
public class Ui extends Application {

    private Duke duke;

    /**
     * A constructor for the Ui.
     *
     * @throws IOException If unexpected IO errors occur when initializing Duke.
     */
    public Ui() throws IOException {
        duke = new Duke();
    }

    /**
     * Starts the Ui with the given stage.
     *
     * @param stage The stage for javaFX scene.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Ui.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
