package duke;

import duke.util.Ui;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Duke duke;
    private Stage stage;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/Unknown.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/PoopEmoji.png"));
    private String exitMessage = new Ui().formatExitMessage().toString();

    /**
     * Initialises the main window controller.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(
                        "Good day there! I'm DUKE\nWhat can I do for you?", dukeImage)
        );
    }

    /**
     * Sets duke for the main window controller
     *
     * @param d Instance of duke to be set.
     */
    public void setDuke(Duke d) {
        duke = d;
        String message = d.loadTaskList().toString();
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(
                        message, dukeImage)
        );
    }

    /**
     * Sets stage for the main window to close.
     *
     * @param stage Instance of stage to be set.
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
        if (response.compareTo(exitMessage) == 0) {
            stage.hide();
        }
    }
}
