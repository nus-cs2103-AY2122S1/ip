package duke.gui;
import duke.Duke;
import duke.DukeException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

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

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Sends a welcome message dialog box.
     */
    public void setWelcomeMessage() {
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(
                "Welcome, I am Duke++\nHow may I help you?",
                false)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        boolean isError = false;
        String response;
        String input = userInput.getText();
        try {
            response = duke.getResponse(input);
        } catch (DukeException e) {
            response = e.getMessage();
            isError = true;
        }
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input),
                DialogBox.getDukeDialog(response, isError)
        );
        userInput.clear();
    }
}

