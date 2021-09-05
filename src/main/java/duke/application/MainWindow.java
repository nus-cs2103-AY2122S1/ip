package duke.application;

import duke.io.Parser;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/UserIcon.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DukeIcon.png"));

    private Parser parser;

    /**
     * Initializes settings, and greets user.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        addDukeDialog("Hello! I am Duke. How may I help you?");
    }

    /**
     * Sets the parser.
     *
     * @param parser Parser to be set.
     */
    public void setParser(Parser parser) {
        this.parser = parser;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        userInput.clear();
        addUserDialog(input);
        String response = parser.getResponse(input);
        addDukeDialog(response);
    }

    /**
     * Adds a dialog for the user to reflect user input being taken.
     *
     * @param input User input.
     */
    private void addUserDialog(String input) {
        dialogContainer.getChildren().add(DialogBox.getUserDialog(input, userImage));
    }

    /**
     * Adds a dialog for Duke to reflect Duke's response.
     *
     * @param response Response from Duke.
     */
    private void addDukeDialog(String response) {
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(response, dukeImage));
    }
}
