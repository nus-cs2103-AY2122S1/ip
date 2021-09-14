package duke.gui;

import duke.exception.DukeException;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/image/user.jpeg"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/image/duke.jpeg"));

    /**
     * Initializes the property of MainWindow and show welcome message from Duke.
     */
    @FXML
    public void initialize() {
        userInput.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 14));
        sendButton.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 14));
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(DukeGUI.command.welcomeToUser().toString(), dukeImage));
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        try {
            DukeGUI.command.loadSavedTasks();
        } catch (IOException ioException) {
            String messageToUser = "Sorry, there is something wrong with the given file. I cannot load" +
                    "current tasks.";
            dialogContainer.getChildren().add(DialogBox.getDukeDialog(messageToUser, dukeImage));
        } catch (DukeException dukeException) {
            dialogContainer.getChildren().add(DialogBox.getDukeDialog(dukeException.getMessage(), dukeImage));
        }
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        assert userImage != null : "User's Image should not be null";
        assert dukeImage != null : "Duke's Image should not be null";
        String input = userInput.getText();
        String response = DukeGUI.command.getCorrespondingMessage(input).toString();
        assert !response.isEmpty() : "response should not be an empty String";
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
        if (input.equals("bye")) {
            exit();
        }
    }

    private void exit() {
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.schedule(Platform::exit, 2, TimeUnit.SECONDS);
    }
}