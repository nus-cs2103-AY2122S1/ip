package duke.ui;

import duke.main.Duke;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

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

    private Image userImage = new Image((this.getClass().getResourceAsStream("/images/DaUser.png")));

    private Image dukeImage = new Image((this.getClass().getResourceAsStream("/images/DaDuke.png")));

    private String welcomeMessage =
            " ____       _________\\\n"
            + "|  _ \\ _   _| |______/\n"
            + "| | | | | | | |     \n"
            + "| |_| | |_| | |______|\n"
            + "|____/ \\__,_|_|______|\n";

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
    private void handleUserInput() throws InterruptedException {
        String input = userInput.getText();
        if (input.equalsIgnoreCase("bye")) {
            Platform.exit();
        }
        String response = duke.reply(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage)
        );
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(response, dukeImage),
                DialogBox.getDukeDialog("What else can I do for you?", dukeImage)
        );
        userInput.clear();
    }
}
