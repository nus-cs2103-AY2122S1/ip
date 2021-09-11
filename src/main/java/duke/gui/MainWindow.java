package duke.gui;

import java.util.Timer;
import java.util.TimerTask;

import duke.Duke;
import duke.Ui;
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

    private Duke bot;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.jpeg"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/bot.jpeg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke d) {
        bot = d;
        start();
    }

    /**
     * Starts up the Main Window of the GUI.
     */
    public void start() {
        sendButton.setStyle("-fx-background-color: #52be80; -fx-background-radius:80");
        Ui userInterface = bot.getUi();
        String greetings = userInterface.greet(bot.getTasks());
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(greetings, dukeImage));
    }


    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = bot.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();

        if (input.equals("bye")) {
            new Timer().schedule(
                    new TimerTask() {
                        public void run() {
                            Platform.exit();
                            System.exit(0); }
                    }, 3000);
        }
    }
}
