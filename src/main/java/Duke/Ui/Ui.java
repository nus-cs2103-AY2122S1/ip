package Duke.Ui;

import java.io.IOException;

import Duke.Duke;
import Duke.Ui.Controllers.MainWindow;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Graphical User Interface for Duke.
 */
public class Ui {
    public static final String GREETING_MESSAGE = "Hello I'm Duke!";
    public static final String EXIT_MESSAGE = "Bye bye! Hope you have a productive day :)";
    public static final String ERROR_MESSAGE = "Oops! An error occurred: %s";
    public static final String FILE_FORMAT_ERROR_MESSAGE = "The file storing your tasks is in an unrecognized format. "
            + "Please fix or remove it.";

    private MainWindow mainController;

    /**
     * Constructs a new instance of the GUI associated with the specified Duke and Stage.
     *
     * @throws IOException If an error occurs when loading the fxml.
     */
    public Ui(Stage stage, Duke duke) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/MainWindow.fxml"));
        AnchorPane ap = fxmlLoader.load();
        Scene scene = new Scene(ap);
        stage.setScene(scene);
        mainController = fxmlLoader.getController();
        mainController.setDuke(duke);
        stage.show();
    }

    public void addDukeMessage(String message) {
        mainController.addDukeMessage(message);
    }
}
