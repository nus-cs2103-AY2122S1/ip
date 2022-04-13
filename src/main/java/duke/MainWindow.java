package duke;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 *
 * @author Samay Sagar
 * @version CS2103 AY21/22 Sem 1
 */
public class MainWindow extends AnchorPane {
    private static final String GREETING_MESSAGE =
            "Hello! I'm Pikachu\n"
                    + "Welcome to PokéTaskList!\n"
                    + "Here are some commands to start with:\n"
                    + "1. list\n"
                    + "2. t <task>\n"
                    + "3. e <task> /at YYYY-MM-DD\n"
                    + "4. d <task> /by YYYY-MM-DD\n"
                    + "5. bye";

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/snorlax.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/pika.png"));


    /**
     * Initializes the MainWindow along with the greeting message.
     */
    @FXML
    public void initialize() {
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(GREETING_MESSAGE, dukeImage)
        );
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

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
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }
}
