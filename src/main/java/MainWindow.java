import duke.Duke;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    private String logo =
              " ___\n"
            + "|   _   \\\n"
            + "|  |_|   |\n"
            + "|  __ /\n"
            + "|   |\n"
            + "|_|\n";

    private Text text = new Text(logo);

    /**
     * Initializes the interface with a welcome message
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(logo + "Hi, welcome back! ☻\nWhat would you like to do?", dukeImage));
    }

    /**
     * Sets the Duke object that is used
     *
     * @param d the Duke object to be used
     */
    public void setDuke(Duke d) {
        this.duke = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input);
        // set color here to red if theres error
        DialogBox db = DialogBox.getDukeDialog(response, dukeImage);
        if (this.duke.getHasError()) {
            db.setStyle("-fx-background-color: #dec4c8;");
        }
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                db
        );
        userInput.clear();
        if (duke.isExit()) {
            Platform.exit();
        }
    }
}
