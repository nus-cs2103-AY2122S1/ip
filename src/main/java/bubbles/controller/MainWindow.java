package bubbles.controller;

import java.util.Timer;
import java.util.TimerTask;

import bubbles.Bubbles;
import bubbles.util.Message;
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

    private Bubbles bubbles;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/Blossom.png"));
    private final Image bubblesImage = new Image(this.getClass().getResourceAsStream("/images/Bubbles.png"));

    /**
     * Initializes the components of the Main Window.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(DialogBox.getBubblesDialog(Message.WELCOME.toString(), bubblesImage));
    }

    public void setBubbles(Bubbles b) {
        bubbles = b;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Bubble's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = bubbles.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getBubblesDialog(response, bubblesImage)
        );
        userInput.clear();

        // After receiving the exit message from user, shut down the bot
        if (response.equals(Message.EXIT.toString())) {
            userInput.setDisable(true);
            sendButton.setDisable(true);

            // Display the exit message and then exit after 1s
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    System.exit(0);
                }
            }, 2000);
        }
    }
}
