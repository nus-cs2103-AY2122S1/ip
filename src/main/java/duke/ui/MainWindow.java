package duke.ui;

import java.util.Objects;

import duke.main.Duke;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
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

    private Duke duke;

    private final Image userImage = new Image(Objects.requireNonNull(this.getClass()
            .getResourceAsStream("/images/chad.jpg")));

    private final Image dukeImage = new Image(Objects.requireNonNull(this.getClass()
            .getResourceAsStream("/images/mac.jpg")));

    private String welcomeMessage = "Hello from\n" +
            " ____       _________\\\n"
            + "|  _ \\ _   _| |______/\n"
            + "| | | | | | | |     \n"
            + "| |_| | |_| | |______|\n"
            + "|____/ \\__,_|_|______|\n";

    /**
     * Initialize Duke program with custom message
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(welcomeMessage, dukeImage)
        );
        PauseTransition pause = new PauseTransition(Duration.seconds(2));
        pause.setOnFinished(event -> {
            dialogContainer.getChildren().addAll(
                    DialogBox.getDukeDialog("How may I be at your service?", dukeImage)
            );
        });
        pause.play();
    }

    public void setDuke(Duke duke) {
        this.duke = duke;
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        PauseTransition pause1 = new PauseTransition(Duration.seconds(2));
        PauseTransition pause2 = new PauseTransition(Duration.seconds(3));
        if (input.equalsIgnoreCase("bye")) {
            dialogContainer.getChildren().add(
                    DialogBox.getDukeDialog("Goodbye :)", dukeImage)
            );
            pause1.setOnFinished((event) -> {
                Platform.exit();
            });
            pause1.play();
        } else {
            String response = duke.respondWith(input);
            dialogContainer.getChildren().add(
                    DialogBox.getUserDialog(input, userImage)
            );
            userInput.clear();
            pause1.setOnFinished((event) -> {
                dialogContainer.getChildren().add(
                        DialogBox.getDukeDialog(response, dukeImage)
                );
            });
            pause2.setOnFinished(event -> {
                dialogContainer.getChildren().add(
                        DialogBox.getDukeDialog("What else can I do for you?", dukeImage)
                );
            });
            pause1.play();
            pause2.play();
        }
    }
}
