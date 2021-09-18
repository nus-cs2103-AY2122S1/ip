import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;
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
    private static final String FAREWELL = "Bye. Hope to see you again soon! \\_(\"v\")_/";
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
    private boolean isLastInput;
    /**
     * Method to display the greeting and bind the values.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        showWelcome();
        isLastInput = false;
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() throws InterruptedException {
        if (!isLastInput) {
            String input = userInput.getText();
            if (!input.trim().equals("")) {
                String response = duke.getResponse(input);
                dialogContainer.getChildren().addAll(
                        DialogBox.getUserDialog(input, userImage),
                        DialogBox.getDukeDialog(response, dukeImage)
                );
            }
            userInput.clear();
            if (input.trim().equals("bye")) {
                isLastInput = true;
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        Platform.exit();
                        System.exit(0);
                    }
                }, 3000);
            }
        } else {
            userInput.clear();
        }
    }

    @FXML
    private void showWelcome() {
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(GREETING, dukeImage));
    }

    @FXML
    private void showGoodbye() {
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(FAREWELL, dukeImage));
    }
}
