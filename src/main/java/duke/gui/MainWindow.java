package duke.gui;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import duke.Duke;

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

    private Duke duke;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/mallow.jpg"));
    private final Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/herb.png"));

    /**
     * Initializes the scroll pane.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Sets the Duke instance.
     *
     * @param d The Duke instance to be used
     */
    public void setDuke(Duke d) {
        duke = d;
        this.handleUserInput(duke.getWelcomeMessage());
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

        if (duke.isExit()) {
            // Adapted from https://stackoverflow.com/questions/58707689/is-it-possible-to-schedule-a-completablefuture
            CompletableFuture.delayedExecutor(2L, TimeUnit.SECONDS).execute(Platform::exit);
        }
    }

    @FXML
    private void handleUserInput(String welcome) {
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(welcome, dukeImage)
        );
        userInput.clear();
    }
}
