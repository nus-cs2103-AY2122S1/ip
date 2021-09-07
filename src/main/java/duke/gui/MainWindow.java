package duke.gui;

import duke.exception.DukeException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/image/user.jpeg"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/image/duke.jpeg"));

    /**
     * Initialize the property of MainWindow and show welcome message from Duke.
     */
    @FXML
    public void initialize() {
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(Duke.command.welcomeToUser().toString(), dukeImage));
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        try {
            Duke.command.loadSavedTasks();
        } catch (IOException ioException) {
            String messageToUser = "Sorry, there is something wrong with the given file. I cannot load" +
                    "current tasks.";
            dialogContainer.getChildren().add(DialogBox.getDukeDialog(messageToUser, dukeImage));
        } catch (DukeException dukeException) {
            dialogContainer.getChildren().add(DialogBox.getDukeDialog(dukeException.getMessage(), dukeImage));
        }
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        assert userImage != null : "User's Image should not be null";
        assert dukeImage != null : "Duke's Image should not be null";
        String input = userInput.getText();
        String response = Duke.command.getCorrespondingMessage(input).toString();
        assert response.equals("") : "response should not be an empty String";
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }
}