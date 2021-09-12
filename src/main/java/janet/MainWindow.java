package janet;

import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 *
 * @@author wpinrui-reused
 * Code reused from Jeffry Lum (https://se-education.org/guides/tutorials/javaFxPart4.html)
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

    private Janet janet;

    private final Image userImage = new Image(Objects.requireNonNull(
            this.getClass().getResourceAsStream("/images/Eleanor.png")));
    private final Image janetImage = new Image(Objects.requireNonNull(
            this.getClass().getResourceAsStream("/images/Janet.png")));

    public MainWindow() {
        janet = new Janet();
    }

    /**
     * Initialises the scroll pane and displays a greeting to the user.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        addDialogs(makeJanetDialog(Ui.INTRO_STRING));
    }

    /**
     * Sets the instance of Janet associated with the chat window.
     *
     * @param janet Instance of Janet
     */
    public void setJanet(Janet janet) {
        assert(janet != null);
        this.janet = janet;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Janet's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() throws IOException {
        String input = userInput.getText();
        Command command = janet.getCommandFromInput(input);
        String response = janet.getResponseFromCommand(command);
        addDialogs(makeUserDialog(input), makeJanetDialog(response));
        userInput.clear();
    }

    private void addDialogs(DialogBox... dialogs) {

        dialogContainer.getChildren().addAll(dialogs[0]);
        if (dialogs.length != 1) {
            addDialogs(Arrays.copyOfRange(dialogs, 1, dialogs.length));
        }
    }

    private DialogBox makeJanetDialog(String message) {
        return DialogBox.getJanetDialog(message, janetImage);
    }

    private DialogBox makeUserDialog(String message) {
        return DialogBox.getUserDialog(message, userImage);
    }
}
