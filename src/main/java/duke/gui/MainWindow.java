package duke.gui;

import duke.Duke;
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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/eve.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/walle.png"));

    /**
     * Initialize Duke GUI.
     */
    @FXML
    public void initialize() {
        this.duke = new Duke();
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        if (duke.isFirstTimeUser()) {
            tutorial();
        } else {
            welcomeUser();
        }

    }

    private void tutorial() {
        String tutorial = "Welcome!!! My name is Duke, your personal assistant!\n"
                + "To get started, simply add a task by using the 'todo/deadline/event' command!";
        String accessList = "To see all your items, enter 'list' command!";
        String exit = "To save changes and exit, enter 'bye' command!";
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(tutorial, dukeImage),
                DialogBox.getDukeDialog(accessList, dukeImage),
                DialogBox.getDukeDialog(exit, dukeImage));
    }

    private void welcomeUser() {
        String welcomeMessage = String.format("%s\n%s\n%s", "Hello! I'm Duke :)", "What can I do for you?",
                "(Type 'help' to see what I can do!)");
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(welcomeMessage, dukeImage));
    }

    public void setDuke(Duke duke) {
        this.duke = duke;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input);

        // @@author CheyanneSim-reused
        // with minor modifications
        if (response.contains("Data has been saved! Goodbye!")) {
            PauseTransition pause = new PauseTransition(Duration.seconds(1.5));
            pause.setOnFinished(event -> {
                Platform.exit();
            });
            pause.play();
        }

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }


}
