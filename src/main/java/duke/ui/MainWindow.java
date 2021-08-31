package duke.ui;

import duke.Duke;
import duke.command.Command;
import duke.command.CommandResult;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DogRight.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DogLeft.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke duke) {
        this.duke = duke;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        CommandResult result = duke.getResponse(input);
        dialogContainer.getChildren().add(DialogBox.getUserDialog(input, userImage));
        handleCommandResult(result);
        userInput.clear();
    }

    private void handleCommandResult(CommandResult result) {
        if (result.isReply()) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getDukeDialog(result.getFeedbackToUser(), dukeImage)
            );
        } else {
            dialogContainer.getChildren().add(ErrorBox.getErrorBox(result.getFeedbackToUser()));
        }

        if (result.isExit()) {
            handleExit();
        }
    }

    private void handleExit() {

    }
}