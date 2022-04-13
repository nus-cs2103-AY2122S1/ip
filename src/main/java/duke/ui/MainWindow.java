package duke.ui;

import duke.Duke;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Adapted from https://se-education.org/guides/tutorials/javaFxPart4.html
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private final Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));
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
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String rawInput = userInput.getText();
        this.printUserMessage(rawInput);
        duke.handleInput(rawInput);
        userInput.clear();
    }

    /**
     * Prints the user's message in the GUI.
     *
     * @param message The message to be printed.
     */
    public void printUserMessage(String message) {
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(message, userImage)
        );
    }

    /**
     * Prints duke's message in the GUI.
     *
     * @param message The message to be printed.
     */
    public void printDukeMessage(String message) {
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(message, dukeImage)
        );

    }
}
