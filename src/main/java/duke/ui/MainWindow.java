package duke.ui;

import duke.Duke;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.jpg"));
    private final Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Greets users.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        String welcome = "Hello! I'm Duke\nWhat can I do for you?\n";
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(welcome, dukeImage));
    }

    /**
     * Sets Duke for the current window.
     * @param d Duke for the current window.
     */
    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        dialogContainer.getChildren().add(DialogBox.getUserDialog(input, userImage));
        String response = duke.parseExecuteAndGetResponse(input);
        if (response.contains("OOPs")) {
            dialogContainer.getChildren().add(DialogBox.getDukeFailureDialog(response, dukeImage));
        } else {
            dialogContainer.getChildren().add(DialogBox.getDukeDialog(response, dukeImage));
        }
        userInput.clear();
        if (response.contains("Bye")) {
            Stage stage = (Stage) sendButton.getScene().getWindow();
            stage.close();
        }
    }
}
