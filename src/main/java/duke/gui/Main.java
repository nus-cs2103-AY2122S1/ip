package duke.gui;

import java.io.IOException;

import duke.Duke;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Represents how the Duke GUI is launched.
 *
 * @author Javier Phon Zhee Kai.
 * @version CS2103T AY21/22 Sem 1.
 */
public class Main extends Application {
    /** The file path to save user data. */
    private static final String FILEPATH = "data/duke.txt";

    /** An instance of Duke. */
    private final Duke duke = new Duke(FILEPATH);

    /**
     * The main entry point for all JavaFX applications.
     * The start method is called after the initialise method has returned,
     * and after the system is ready for the application to begin running.
     *
     * @param stage The stage for this application, onto which the application scene can be set.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            scene.getRoot().setStyle("-fx-font-family: 'serif'");
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.setTitle("Welcome to Duke!");
            stage.show();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
