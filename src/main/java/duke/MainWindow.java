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
    private Ui ui = new Ui();

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.jpg"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.jpg"));

    /**
     * Initialises the MainWindow.
     */
    @FXML
    public void initialize() {
        // Adapted the following snippet from https://github.com/gordonlzy/ip/
        scrollPane.setStyle("-fx-background: transparent; -fx-background-color: transparent;");
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        sendButton.setStyle("-fx-background-color: #2b2b2b; -fx-text-fill: white");
        dialogContainer.setStyle("-fx-background-color: #2b2b2b; -fx-text-fill: white");
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(ui.showWelcome(), dukeImage));
    }

    /**
     * Setter for Duke.
     *
     * @param d Instance of Duke used.
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
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }
}
