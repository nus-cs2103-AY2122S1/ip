package duke.control;

import java.util.Timer;
import java.util.TimerTask;

import duke.Duke;
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

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/jotaro.png"));
    private final Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/dio.png"));
    private final String welcomeMessage = new Ui().showWelcome();

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke d) {
        duke = d;
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(false, welcomeMessage, dukeImage)
        );
    }

    // Exit Duke after 2 seconds if the user input "bye"
    private void delayedExit(String input) {
        boolean isExitCommand = input.equals("bye");
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            public void run() {
                Platform.exit();
            }
        };
        int delayedTime = 2000;
        if (isExitCommand) {
            timer.schedule(timerTask, delayedTime);
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
        boolean isErrorMessage = response.startsWith("Wryyyyy!");
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(isErrorMessage, response, dukeImage)
        );
        userInput.clear();
        delayedExit(input);
    }
}
