package duke;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends BorderPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private HBox inputContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Duke duke;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.jpg"));
    private final Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.jpg"));

    /**
     * Initializes the main window.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        scrollPane.getStylesheets().add(this.getClass().getResource("/style/style.css").toExternalForm());
        scrollPane.setFitToWidth(true);

        inputContainer.getStylesheets().add(this.getClass().getResource("/style/style.css").toExternalForm());
    }

    public void setDuke(Duke d) {
        duke = d;

        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(duke.getWelcome(), dukeImage));
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(duke.getSavedTasks(), dukeImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        assert (duke != null) : "Duke is not initialised!";

        String input = userInput.getText();
        String response = duke.getResponse(input);

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );

        userInput.clear();
    }

    /**
     * Gets the user input history and display to the text field.
     * Sets the caret position to the last position.
     */
    @FXML
    private void handleTextFieldKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.UP) {
            userInput.setText(duke.getLastInput());
            userInput.positionCaret(userInput.getLength());
        }
        if (event.getCode() == KeyCode.DOWN) {
            userInput.setText(duke.getNextInput());
            userInput.positionCaret(userInput.getLength());
        }
    }
}
