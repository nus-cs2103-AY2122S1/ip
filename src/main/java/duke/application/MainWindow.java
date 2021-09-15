package duke.application;

import java.util.ArrayList;

import duke.io.Parser;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
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

    // To keep track of input history so user can switch between past inputs
    private ArrayList<String> pastInputs = new ArrayList<>();
    private int inputIndex = -1;
    private String currentInput;

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
        // add the input to the front of the past inputs
        pastInputs.add(0, input);
        // reset the selected history
        inputIndex = -1;
        // if there is not input, no need to have any action
        if (!input.equals("")) {
            userInput.clear();
            addUserDialog(input);
            String response = parser.getResponse(input);
            addDukeDialog(response);
        }
    }

    /**
     * Switches between past inputs when user keys up or down.
     *
     * @param keyEvent The key that was pressed.
     */
    @FXML
    private void handleUserKeystroke(KeyEvent keyEvent) {
        if (inputIndex == -1) {
            // save the current input so it's accessible when returning
            currentInput = userInput.getText();
        }

        switch (keyEvent.getCode()) {
        case UP:
            if (inputIndex + 1 < pastInputs.size()) {
                inputIndex++;
                userInput.setText(pastInputs.get(inputIndex));
                userInput.end();
            }
            break;
        case DOWN:
            if (inputIndex > -1) {
                inputIndex--;
                String newText = inputIndex == -1 ? currentInput : pastInputs.get(inputIndex);
                userInput.setText(newText);
                userInput.end();
            }
            break;
        default:
            // nothing special to do otherwise
            break;
        }
    }

    /**
     * Adds a dialog for the user to reflect user input being taken.
     *
     * @param input User input.
     */
    private void addUserDialog(String input) {
        assert dialogContainer != null;
        dialogContainer.getChildren().add(DialogBox.getUserDialog(input, userImage));
    }

    /**
     * Adds a dialog for Duke to reflect Duke's response.
     *
     * @param response Response from Duke.
     */
    private void addDukeDialog(String response) {
        assert dialogContainer != null;
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(response, dukeImage));
    }
}
