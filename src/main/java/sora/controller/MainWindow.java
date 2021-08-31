package sora.controller;

import java.util.Timer;
import java.util.TimerTask;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import sora.Sora;
import sora.util.Message;

/**
 * Controller for Main Window.
 * Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private final Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    private Sora sora;

    /**
     * Initialize components of the Main Window.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(Message.WELCOME.toString(), dukeImage));
    }

    public void setDuke(Sora sora) {
        this.sora = sora;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Sora's reply
     * and then appends them to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        // Obtain user input
        String input = userInput.getText();

        // Obtain and display response to user input
        String response = sora.getResponse(input);
        dialogContainer.getChildren().addAll(
            DialogBox.getUserDialog(input, userImage),
            DialogBox.getDukeDialog(response, dukeImage)
        );

        // Remove previous input
        userInput.clear();

        // Shutdown upon receiving exit message
        if (response.equals(Message.EXIT.toString())) {
            // Disable all mode of input
            userInput.setDisable(true);
            sendButton.setDisable(true);

            // Display the exit message and then exit after 1s
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    System.exit(0);
                }
            }, 1000);
        }
    }
}
