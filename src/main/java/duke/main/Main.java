package duke.main;

import java.io.IOException;

import duke.controllers.MainWindow;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Main class encapsulates the initialization process of the application.
 *
 * @author Jason Ng
 * @version Duke Level-10
 */
public class Main extends Application {
    /**
     * Start method to start the entire application.
     * Loads the relevant FXML files to render the GUI.
     *
     * @param stage The stage to set the scene for the GUI.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(new Duke());
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

