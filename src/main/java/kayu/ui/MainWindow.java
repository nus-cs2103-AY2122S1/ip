package kayu.ui;

import java.util.Objects;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import kayu.Kayu;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {

    // Image paths (starting from /src/main/java/resources).
    private static final String KAYU_IMAGE_PATH = "/images/duke.png";
    private static final String USER_IMAGE_PATH = "/images/user.png";
    
    @FXML
    private ScrollPane scrollPane;
    
    @FXML
    private VBox dialogContainer;
    
    @FXML
    private TextField userInput;
    
    @FXML
    private Button sendButton;

    private Kayu kayu;
    private Image userImage;
    private Image dukeImage;

    /**
     * Initializes the UI window for user to interact with.
     */
    @FXML
    public void initialize() {
        userImage = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream(USER_IMAGE_PATH)));
        dukeImage = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream(KAYU_IMAGE_PATH)));
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        
        initialiseKayu();
    }

    /**
     * Initializes the Kayu program.
     */
    private void initialiseKayu() {
        kayu = new Kayu();
        kayu.initialize();
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(kayu.getGreeting(), dukeImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText().trim();
        dialogContainer.getChildren().add(DialogBox.getUserDialog(input, userImage));
        userInput.clear();
        
        String response = kayu.getResponse(input);
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(response, dukeImage));
        
        if (kayu.isRecentCommandBye()) {
            kayu.exit();
        }
    }
}
