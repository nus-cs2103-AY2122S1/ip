package duke;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {

    private static final int TIMER_DELAY = 700;

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Duke duke;
    private String dukeGreeting = "Hello! I'm DukeBot\nWhat can I do for you?";

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Displays greeting message to user upon program starting.
     * Notifies VBox to react to its size changing and scroll automatically.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        userInput.setPromptText("Enter a command here: ");
        dialogContainer.getChildren().addAll(
                    DialogBox.getDukeDialog(dukeGreeting, dukeImage));
    }

    public void setDuke(Duke duke) {
        this.duke = duke;
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        assert !input.isEmpty() : "User input cannot be empty!";
        String response = duke.getResponse(input);

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );

        handleByeInput(input);

        userInput.clear();
    }

    private void handleByeInput(String input) {
        if (input.equals("bye")) {
            //@@author kaushikkrdy-reused
            //Reused from https://stackoverflow.com/a/21996863
            //with minor modifications
            new Timer().schedule(
                    new TimerTask() {
                        public void run() {
                            Platform.exit();
                            System.exit(0);
                        }
                    }, TIMER_DELAY);
            //@@author
        }
    }
}
