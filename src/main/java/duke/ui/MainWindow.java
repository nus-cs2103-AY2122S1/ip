package duke.ui;

import java.io.IOException;
import java.io.InputStream;
import java.util.Timer;
import java.util.TimerTask;

import duke.Duke;
//
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
    private static final String GREETING = "Hello! I'm Duke, what can I do for you?";
    private static Duke duke;

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Image userImage;
    private Image dukeImage;

    /**
     * Initialises the gui.
     */
    @FXML
    public void initialize() {
        initialiseImage();
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        showWelcome();
        showReminders();
    }

    public static void setDuke(Duke d) {
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
        //@@author qinguorui2001-reused
        //Reused from https://github.com/nus-cs2103-AY2122S1/ip/pull/13/files
        // with minor modifications
        if (input.trim().equals("bye")) {
            userInput.setDisable(true);
            sendButton.setDisable(true);
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    System.exit(0);
                }
            }, 1500);
        }
        //@@author
    }

    @FXML
    private void showWelcome() {
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(GREETING, dukeImage));
    }

    @FXML
    private void showReminders() {
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(Duke.showComings(), dukeImage));
    }

    @FXML
    private void initialiseImage() {
        InputStream userStream = this.getClass().getResourceAsStream("/images/user.png");
        assert userStream != null;
        userImage = new Image(userStream);
        InputStream dukeStream = this.getClass().getResourceAsStream("/images/duke.png");
        assert dukeStream != null;
        dukeImage = new Image(dukeStream);
    }
}
