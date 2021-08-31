package duke;

import java.io.IOException;
import java.util.Arrays;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 * Code reused from Jeffry Lum (https://se-education.org/)
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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.jpg"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.jpg"));

    public MainWindow() {
        duke = new Duke(this);
    }

    /**
     * Initialises the scroll pane and displays a greeting to the user.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        addDialogsInChatBox(dukeDialog(Ui.INTRO_STRING));
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() throws IOException {
        String input = userInput.getText();
        String response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }

    void addDialogsInChatBox(DialogBox... dialogs) {
        dialogContainer.getChildren().addAll(dialogs[0]);
        if (dialogs.length != 1) {
            addDialogsInChatBox(Arrays.copyOfRange(dialogs, 1, dialogs.length));
        }
    }

    DialogBox dukeDialog(String message) {
        return DialogBox.getDukeDialog(message, dukeImage);
    }

    DialogBox userDialog(String message) {
        return DialogBox.getUserDialog(message, userImage);
    }
}
