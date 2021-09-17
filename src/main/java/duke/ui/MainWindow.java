package duke.ui;

import java.util.Objects;

import duke.main.DuC;
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

public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private DuC duC;

    private final Image userImage = new Image(Objects.requireNonNull(this.getClass()
            .getResourceAsStream("/images/chad.jpg")));

    private final Image dukeImage = new Image(Objects.requireNonNull(this.getClass()
            .getResourceAsStream("/images/mac.jpg")));

    /**
     * Initialize DuC program with custom message
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        promptDialog();
    }

    public void setDuC(DuC duC) {
        this.duC = duC;
    }

    private void promptDialog() {
        String welcomeMessage = "Hello from\n" +
                " ____       _________ \n"
                + "|  _ \\ _   _| |______/\n"
                + "| | | | | | | |     \n"
                + "| |_| | |_| | |______|\n"
                + "|____/ \\__,_|_|______|\n";

        dialogContainer.getChildren().add(DialogBox.getDukeDialog(welcomeMessage, dukeImage));
        PauseTransition pause = new PauseTransition(Duration.seconds(1.5));
        pause.setOnFinished(event -> {
            String promptQuestion = "How can I take your order?\n" +
                    "Type in 'help' if you don't know where to start.";
            dialogContainer.getChildren().add(DialogBox.getDukeDialog(promptQuestion, dukeImage));
        });
        pause.play();
    }

    private void exitDialog() {
        String exitMessage = "Bye, hope that you can pronounce my name now :)";
        PauseTransition pause1 = new PauseTransition(Duration.seconds(1.5));
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(exitMessage, dukeImage));
        pause1.setOnFinished((event) -> {
            Platform.exit();
        });
        pause1.play();
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        dialogContainer.getChildren().add(DialogBox.getUserDialog(input, userImage));
        userInput.clear();

        if (input.equalsIgnoreCase("bye")) {
            exitDialog();
            return;
        }
        String response = duC.respondWith(input);
        PauseTransition pause1 = new PauseTransition(Duration.seconds(2));
        pause1.setOnFinished((event) -> {
            dialogContainer.getChildren().add(DialogBox.getDukeDialog(response, dukeImage));
        });
        pause1.play();

        PauseTransition pause2 = new PauseTransition(Duration.seconds(3));
        pause2.setOnFinished(event -> {
            String promptQuestion = "What else can I help you with?";
            dialogContainer.getChildren().add(DialogBox.getDukeDialog(promptQuestion, dukeImage));
        });
        pause2.play();
    }
}
