package duke.gui;

import java.io.IOException;

import duke.Duke;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * This Main class serves as a GUI for Duke, bridging between
 * the existing logic in Duke and the UI in MainWindow.
 */
public class Main extends Application {

    private Duke duke = new Duke();

    /**
     * Brings up the window for the GUI.
     *
     * @param stage The primary stage that JavaFX provides.
     */
    @Override
    public void start(Stage stage) {
        try {
            stage.setTitle("Duke");
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
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
