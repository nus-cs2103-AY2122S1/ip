package duke.ui;

import java.io.IOException;

import duke.logic.Duke;
import duke.logic.commands.ExitCommand;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 * Directs the flow and interaction between UI elements and the program's logic.
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

    /** The program's logic component */
    private Duke duke;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/BreadDoge.png"));
    private final Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DukeMask.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setUpDuke(Duke d) {
        duke = d;
        String initMessage = "Hello! I am Duke the Java mascot.\nWhat can I do for you?";
        DialogBox initDialog = DialogBox.getDukeDialog(initMessage, dukeImage);
        dialogContainer.getChildren().add(initDialog);
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply
     * then appends them to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();

        if (ExitCommand.isExit(input)) {
            this.exit();
        }
    }

    private void exit() {
        Duration timeout = Duration.seconds(1);
        try {
            duke.end();
        } catch (IOException e) {
            timeout = Duration.seconds(4);
            String errorMsg = "Could not save your tasks:\n  " + e.getMessage()
                    + "\nAll recent changes are not saved.";
            DialogBox errorDialog = DialogBox.getDukeDialog(errorMsg, dukeImage);
            dialogContainer.getChildren().add(errorDialog);
        }
        PauseTransition delay = new PauseTransition(timeout);
        delay.setOnFinished(event -> System.exit(0));
        delay.play();
    }
}
