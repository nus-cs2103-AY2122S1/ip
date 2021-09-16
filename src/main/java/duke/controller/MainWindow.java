package duke.controller;

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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user-male.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/Meap.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        this.sendMessageFromDuke(Ui.LOGO + "\n" + Ui.WELCOME_STATEMENT);
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    private void sendMessageFromDuke(String text) {
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(text, dukeImage)
        );
    }

    private void terminateAfterXSeconds(int secondsBeforeTerminate) {
        new java.util.Timer().schedule(
            new java.util.TimerTask() {
                @Override
                public void run() {
                    //Platform.exit();
                    System.exit(0);
                }
            },
            secondsBeforeTerminate * 1000
        );
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

        //handles termination command "bye"
        if (input.equals("bye")) {
            this.terminateAfterXSeconds(2);
        }
    }
}