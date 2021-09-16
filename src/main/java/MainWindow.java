import javafx.application.Platform;
import javafx.fxml.FXML;
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

    private Duke duke;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private final Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /** Initialises the UI of the chatBot */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        handleGreeting();
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

        if (input.equals("bye")) {
            handleExit();
        }

        String formattedResponse = "Duke\n\n" + duke.getResponse(input);

        String formattedInput = "You\n\n" + input;

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(formattedInput, userImage),
                DialogBox.getDukeDialog(formattedResponse, dukeImage)
        );
        userInput.clear();
    }

    /** Closes the program */
    private void handleExit() {
        Platform.exit();
    }

    /** Greets the user on launch */
    private void handleGreeting() {
        String initialGreetingMessage = "Hello! I'm Duke. "
                + "\nWhat can I do for you?";
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(initialGreetingMessage, dukeImage)
        );
    }
}
