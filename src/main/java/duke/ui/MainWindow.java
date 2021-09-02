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
            .getResourceAsStream("/images/DaUser.png")));

    private final Image dukeImage = new Image(Objects.requireNonNull(this.getClass()
            .getResourceAsStream("/images/DaDuke.png")));

    private String welcomeMessage =
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
        welcomeMessage = "Hello from\n" + welcomeMessage;
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(welcomeMessage, dukeImage),
                DialogBox.getDukeDialog("What can I do for you today?", dukeImage)
        );
    }

    public void setDuke(Duke duke) {
        this.duke = duke;
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        PauseTransition pause = new PauseTransition(Duration.seconds(3));
        if (input.equalsIgnoreCase("bye")) {
            dialogContainer.getChildren().add(
                    DialogBox.getDukeDialog("Exiting Duke...", dukeImage)
            );
            pause.setOnFinished((event) -> {
                Platform.exit();
            });
        } else {
            String response = duke.respondWith(input);
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage)
            );
            userInput.clear();
            pause.setOnFinished((event) -> {
                dialogContainer.getChildren().addAll(
                        DialogBox.getDukeDialog(response, dukeImage),
                        DialogBox.getDukeDialog("What else can I do for you?", dukeImage)
                );
            });
        }
        pause.play();
    }
}
