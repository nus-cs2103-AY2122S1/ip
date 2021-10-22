package duke.ui;

import java.util.Timer;
import java.util.TimerTask;

import duke.Duke;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * The main window where the interaction occurs with duke.
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

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private final Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/Duke.jpg"));

    /**
     * Initializes the main window with the greeting message from duke.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getStylesheets().add("/stylesheets/MainWindow.css");
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog("Greetings! I am Duke." + "\n" + "How can I assist you?", dukeImage)
        );
    }

    /**
     * Sets the instance of duke to one provided.
     *
     * @param d the duke instance that will be use to handle the input.
     */
    public void setDuke(Duke d) {
        duke = d;
    }

    private void exit() {
        new Timer().schedule(new TimerTask() {
            public void run () {
                Platform.exit();
            }
        }, 1000);
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
        if (response.equals(duke.getFarewellMessage())) {
            exit();
        }
    }
}
