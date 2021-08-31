package duke.controller;

import duke.Duke;
import duke.exception.DukeException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * The MainWindow class is a controller class that provides the layout for the other controls.
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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @FXML
    public void initialize() {
        try {
            scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
            duke = new Duke();
        } catch (DukeException e) {
            dialogContainer.getChildren().add(DialogBox.getDukeDialog(e.toString(), dukeImage));
        } finally {
            dialogContainer.getChildren().add(DialogBox.getDukeDialog(duke.getStartMessage(), dukeImage));
        }
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply,
     * then appends them to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        dialogContainer.getChildren().add(DialogBox.getUserDialog(input, userImage));
        String response = duke.getResponse(input);
        userInput.clear();
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(response, dukeImage));
        if (input.equals("bye")) {
            userInput.setEditable(false);
            sendButton.setDisable(true);
        }
    }
}