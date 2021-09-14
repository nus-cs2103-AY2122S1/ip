package jarvis;

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

    private Jarvis jarvis;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image jarvisImage = new Image(this.getClass().getResourceAsStream("/images/DaJarvis.png"));

    /**
     * Opens up the chat box with a greeting.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(
                // Jarvis greets user upon opening the application
                DialogBox.getJarvisDialog(Ui.firstGreeting(), jarvisImage)
        );
    }

    /**
     * Sets up Jarvis.
     *
     * @param d The Jarvis object to be set up.
     */
    public void setJarvis(Jarvis d) {
        jarvis = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Jarvis' reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = jarvis.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage), // User's dialog box
                DialogBox.getJarvisDialog(response, jarvisImage) // Jarvis' dialog box
        );
        userInput.clear();
    }
}

