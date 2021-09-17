package duke;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import duke.command.Command;
import duke.util.DialogBox;
import duke.util.DukeException;
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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/Jerry.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/Meeseeks.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke d) {
        duke = d;
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(d.initDuke(), dukeImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response;
        try {
            Command command = duke.getCommand(input);
            assert command != null : "Why is command null?!";
            if (command.isExit()) {
                CompletableFuture.delayedExecutor(3, TimeUnit.SECONDS).execute(Platform::exit);
            }
            response = command.execute(this.duke.storage, this.duke.taskList);
        } catch (DukeException e) {
            response = e.getMessage();
        }
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(" You said: \n " + input, userImage),
                DialogBox.getDukeDialog("======= Oooo yeah! ========\n  "
                        + response
                        + "\n" , dukeImage)
        );
        userInput.clear();
    }
}
