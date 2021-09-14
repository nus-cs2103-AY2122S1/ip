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
    private static final String USER_IMAGE = "/images/DaUser.png";
    private static final String DUKE_IMAGE = "/images/Baba.jpg";
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Duke duke;

    private Image userImage;
    private Image dukeImage;

    /**
     * Initialises the scroll pane and dialog container.
     */
    @FXML
    public void initialize() {
        assert this.getClass().getResourceAsStream(USER_IMAGE) != null : " User Image not found";
        assert this.getClass().getResourceAsStream(DUKE_IMAGE) != null : " Duke Image not found";

        userImage = new Image(this.getClass().getResourceAsStream(USER_IMAGE));
        dukeImage = new Image(this.getClass().getResourceAsStream(DUKE_IMAGE));

        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        String welcomeMessage = Ui.welcomeMessage();
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(welcomeMessage, dukeImage)
        );
    }

    public void setDuke(Duke d) {
        this.duke = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing
     * Duke's reply and then appends them to the dialog container.
     * Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = Duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }
}
