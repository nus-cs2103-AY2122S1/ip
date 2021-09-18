package cs2103.duke.controllers;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import cs2103.duke.Duke;
import javafx.util.Duration;

/**
 * Controller for cs2103.duke.controllers.MainWindow. Provides the layout for the other controls.
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

    private static final String WELCOME_MESSAGE = "Welcome to Duke! What would you like to do today?";

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/TheUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/TheDuke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(WELCOME_MESSAGE, dukeImage)
        );
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing. Duke's image is placed on the left to
     * differentiate between user input and Duke’s output.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input);

        if (input.equals("bye")) {
            addDialog(input, response);
            // solution for delay before closing the window adapted from:
            // https://github.com/felissaf/ip/blob/master/src/main/java/myjournal/MainWindow.java.
            PauseTransition goodbyePause = new PauseTransition(Duration.seconds(2));
            goodbyePause.setOnFinished(event -> Platform.exit());
            goodbyePause.play();
        } else {
            addDialog(input, response);
            userInput.clear();
        }
    }

    /**
     * A helper method to add dialog for the user and duke.
     *
     * @param input    The user's input.
     * @param response The duke's response.
     */
    private void addDialog(String input, String response) {
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage));
    }
}