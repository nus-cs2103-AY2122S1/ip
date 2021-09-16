package lifeline.controller;

import java.io.File;

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
import lifeline.Lifeline;
import lifeline.command.Command;


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

    private Lifeline lifeline = new Lifeline("save" + File.separator + "tasks.json");

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image lifelineImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Initializes an instance of Lifeline to respond to user input and forces ScrollPane to automatically scroll down.
     * Display DialogBox containing lifeline greeting message and image.
     */
    @FXML
    public void initialize() {
        String greetingMessage = lifeline.getGreetingMessage();
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(DialogBox.getLifelineDialog(greetingMessage, lifelineImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = lifeline.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getLifelineDialog(response, lifelineImage)
        );
        userInput.clear();
        if (Command.BYE.hasCommand(input.toLowerCase().trim())) {
            PauseTransition delay = new PauseTransition(Duration.millis(400));
            delay.setOnFinished(event -> Platform.exit());
            delay.play();
        }
    }
}
