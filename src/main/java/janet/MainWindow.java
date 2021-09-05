package janet;

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

    private Janet janet;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/Eleanor.jpg"));
    private Image janetImage = new Image(this.getClass().getResourceAsStream("/images/Janet.jpg"));

    public MainWindow() {
        janet = new Janet(this);
    }

    /**
     * Initialises the scroll pane and displays a greeting to the user.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        addDialogsInChatBox(janetDialog(Ui.INTRO_STRING));
    }

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
        assert(input != null);
        String response = janet.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getJanetDialog(response, janetImage)
        );
        userInput.clear();
    }

    void addDialogsInChatBox(DialogBox... dialogs) {
        assert(dialogs.length > 0);
        dialogContainer.getChildren().addAll(dialogs[0]);
        if (dialogs.length != 1) {
            addDialogsInChatBox(Arrays.copyOfRange(dialogs, 1, dialogs.length));
        }
    }

    DialogBox janetDialog(String message) {
        return DialogBox.getJanetDialog(message, janetImage);
    }

    DialogBox userDialog(String message) {
        return DialogBox.getUserDialog(message, userImage);
    }
}
