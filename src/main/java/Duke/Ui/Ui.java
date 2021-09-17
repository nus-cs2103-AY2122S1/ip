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
    public static final String GREETING_MESSAGE = "Greetings. My name is Dwight Schrute. You may call me Mr. Schrute.";
    public static final String EXIT_MESSAGE = "You're going to go? I'll miss you so much. Oh, I'm going to cry myself"
            + " to sleep. False. I will not miss you.";
    public static final String ERROR_MESSAGE = "An error occurred. %s";
    public static final String FILE_FORMAT_ERROR_MESSAGE = "The file storing your tasks is in an unrecognized format. "
            + "Please fix or remove it.";

    private final MainWindow mainController;

    /**
     * Constructs a new instance of the GUI associated with the specified Duke and Stage.
     *
     * @throws IOException If an error occurs when loading the fxml.
     */
    public Ui(Stage stage, Duke duke) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/MainWindow.fxml"));
        AnchorPane ap = fxmlLoader.load();
        assert ap != null;

        Scene scene = new Scene(ap);
        stage.setScene(scene);

        mainController = fxmlLoader.getController();
        assert mainController != null;
        mainController.setDuke(duke);

        stage.show();
    }

    public void addDukeMessage(String message) {
        mainController.addDukeMessage(message);
    }
}
