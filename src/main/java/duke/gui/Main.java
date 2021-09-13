package duke.gui;

import java.io.IOException;

import duke.Duke;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Connects the Duke Chatbot logic to the MainWindow GUI.
 */
public class Main extends Application {
    /** Current instance of Duke **/
    private Duke duke = new Duke("data/duke.txt");

    /**
     * Starts the GUI in MainWindow.fxml and links it to the current instance of Duke.
     *
     * @param stage The main window of the GUI.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle("Duke");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setOnCloseRequest((event) -> {
                Platform.exit();
            });
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
