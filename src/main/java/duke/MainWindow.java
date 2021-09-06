package duke;

import java.util.Timer;
import java.util.TimerTask;

import duke.util.Ui;
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
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Duke duke;
    private Ui ui = new Ui();

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.jpeg"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/Ashy.png"));

    @SuppressWarnings("checkstyle:MissingJavadocMethod")
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        showGreetingMessage();
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.run(input);
        if (input.trim().equalsIgnoreCase("bye")) {
            dialogContainer.getChildren().add(DialogBox.getUserDialog(input, userImage));
            showExitMessage();
        } else {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getDukeDialog(response, dukeImage)
            );
        }
        userInput.clear();
    }

    @FXML
    private void showGreetingMessage() {
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(ui.greetingMessage(), dukeImage));
    }

    @FXML
    private void showExitMessage() {
        new Timer().schedule(new TimerTask() {
            public void run () {
                Platform.exit();
                System.exit(0);
            }
        }, 500);
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(ui.exitMessaage(), dukeImage));
    }
}
