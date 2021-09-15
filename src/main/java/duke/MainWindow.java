package duke;

import java.io.IOException;

import duke.misc.Ui;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
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
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.gif"));

    /**
     * Sets some properties before running duke.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        // Display Welcome Message.
        dialogContainer.getChildren().add(DialogBox.getDukeDialog
                (Ui.WELCOME_MSG, dukeImage));

        dialogContainer.setMinHeight(scrollPane.getPrefHeight());
    }

    public void setDuke() {
        try {
            duke = new Duke();
        } catch (IOException e) {
            dialogContainer.getChildren().add(DialogBox.getDukeDialog(
                    e.toString(), dukeImage));
        }
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

        // To simulate a slight pause when chat bot is signaled to exit.
        if (duke.getIsExited()) {
            PauseTransition delay = new PauseTransition(Duration.seconds(3));
            delay.setOnFinished(event -> Platform.exit());
            delay.play();
        }
    }
}
