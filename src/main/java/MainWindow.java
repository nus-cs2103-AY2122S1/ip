import captain.Captain;
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
    public static final String WELCOME_MSG = "\tAhoy there!"
            + "\n\tHow may I assist you today?";
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Captain captain;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/cat.jpg"));
    private Image captainImage = new Image(this.getClass().getResourceAsStream("/images/samoyed.png"));

    /**
     * Sets up the welcome message.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(WELCOME_MSG, captainImage));
    }

    public void setCaptain(Captain c) {
        captain = c;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = captain.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, captainImage)
        );
        userInput.clear();
    }
}

