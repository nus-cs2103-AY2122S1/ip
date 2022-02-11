package duke.controller;

import java.util.Timer;
import java.util.TimerTask;

import duke.Duke;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;


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

    private final String USER_IMG_DIR = "/images/carrot.png";
    private final String DUKE_IMG_DIR = "/images/apple.png";

    private Image userImage = new Image(this.getClass().getResourceAsStream(USER_IMG_DIR));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream(DUKE_IMG_DIR));

    /**
     * Initialize main window with greeting message.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        String greetingMsg = "Hi, I'm Sync-Me Sebby.\n"
                + "I'm here to assist you with tracking and synchronizing of your personal tasks.\n"
                + "Let me know how I can help?";
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(greetingMsg, dukeImage));
    }

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
        String response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();

        // close the GUI window
        if (input.equals("bye")) {
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    System.exit(0);
                }
            }, 1000);
        }

    }
}
