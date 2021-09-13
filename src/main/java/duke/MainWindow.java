package duke;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import duke.command.ExitCommand;
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
    protected Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    protected Image victorImage = new Image(this.getClass().getResourceAsStream("/images/victor.png"));

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Duke duke;

    /**
     * Initializes the scroll pane, set the background and sendButton style and set greetings.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        // Code adapted from https://github.com/yunpeng1234/ip
        scrollPane.setStyle("-fx-background: transparent; -fx-background-color: transparent;");
        sendButton.setStyle("-fx-background-color: #2b2b2b; -fx-text-fill: white");
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(Ui.getGreetings(), victorImage));
    }

    /**
     * Initializes an instance of Duke
     *
     * @param d an instance of Duke
     */
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
        String response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, victorImage)
        );
        userInput.clear();

        if (input.equals(ExitCommand.INSTRUCTION_EXIT)) {
            CompletableFuture.delayedExecutor(1, TimeUnit.SECONDS).execute(Platform::exit);
        }
    }
}
