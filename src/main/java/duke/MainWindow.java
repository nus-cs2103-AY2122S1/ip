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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/chimp.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/batman.png"));

    /**
     * Initialises the mainWindow to display welcome message and handle userInput.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        String logo = "Hello from \n"
                + " ____           _        \n"
                + "|   _  \\ __   _| |  _____ \n"
                + "|  |  |  |  | |  |  |/  / _ \\\n"
                + "|  |_|  |  |_|  |    <  __/\n"
                + "|____/ \\__,_ |_|\\_\\___|\n\n"
                + "What can I do for you today?\n";
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(logo, dukeImage)
        );
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
