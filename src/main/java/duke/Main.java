package duke;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Main class encapsulates a link between the Duke program and the MainWindow GUI
 */
public class Main extends Application {
    /**
     * Initializes the javafx stage to be shown on the GUI
     *
     * @param stage stage to be set on the GUI
     */
    @Override
    public void start(Stage stage) {
        assert stage != null : "stage cannot be null";

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
