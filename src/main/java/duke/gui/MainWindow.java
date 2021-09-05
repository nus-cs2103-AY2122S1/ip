package duke.gui;

import duke.Duke;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {

    private static final String MESSAGE_GREETING = "Hello! I'm Duke\nWhat can I do for you?";
    private static final String MESSAGE_GOODBYE = "Bye. Hope to see you again soon!";

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;

    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/duke.png"));

    /**
     * Initializes the layout.
     */
    @FXML
    public void initialize() {
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(MESSAGE_GREETING, dukeImage));
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Specifies the Duke instance to handle the logic behind the GUI.
     *
     * @param d The Duke instance to implement the logic.
     */
    public void setDuke(Duke d) {
        duke = d;
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();

        // Quits the GUI if the user types "bye"
        if (input.equals("bye")) {
            dialogContainer.getChildren().add(
                    DialogBox.getDukeDialog(MESSAGE_GOODBYE, dukeImage));
            userInput.clear();
            Platform.exit();
        }

        // Executes the appropriate command and displays a message to user
        String response = duke.getResponse(input);
        assert !response.isEmpty() : "A response should be provided to the user";
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }
}
