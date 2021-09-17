package poseidon.ui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import poseidon.Poseidon;

/**
 * Represents a controller for {@code MainWindow} used by JavaFX in creating the GUI.
 * Also provides the layout for the other controls.
 *
 * @author Yeluri Ketan
 * @version CS2103T AY21/22 Sem 1 iP
 */
public class MainWindow extends AnchorPane {

    // Font to be used for the user input box.
    private static final Font USER_INPUT_FONT = Font.loadFont(DialogBox.class
                    .getResource("/fonts/JetBrainsMono-Italic.ttf")
                    .toExternalForm(),
            15);

    // Images to be used for DialogBox(es) and send button.
    private static final Image USER_IMAGE = new Image(MainWindow.class.getResourceAsStream("/images/UserIcon.jpg"));
    private static final Image BOT_IMAGE = new Image(MainWindow.class.getResourceAsStream("/images/BotIcon.png"));
    private static final Image SEND_IMAGE = new Image(MainWindow.class.getResourceAsStream("/images/SendIcon.png"));

    // The scrollable vertical space containing all the dialog boxes.
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;

    // User input box.
    @FXML
    private TextField userInput;

    // Send button and icon used.
    @FXML
    private Button sendButton;
    @FXML
    private ImageView sendButtonImage;

    private Poseidon poseidon;

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * @param pos {@code Poseidon} to be set as logical unit for the GUI.
     */
    public void setPoseidon(Poseidon pos) {
        poseidon = pos;
        dialogContainer.getChildren().addAll(DialogBox.getBotDialog(poseidon.runWelcome(), BOT_IMAGE));
        sendButtonImage.setImage(SEND_IMAGE);
        userInput.setFont(USER_INPUT_FONT);
    }

    /**
     * Creates two {@code DialogBox}es, one echoing the user input and the other containing the Bot's reply and
     * then appends them to the dialog container. Also clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        final String emptyString = "";

        String input = userInput.getText();

        if (input.equals(emptyString)) {
            return;
        }

        if (poseidon.isBye(input)) {
            Platform.exit();
            return;
        }

        String response = poseidon.run(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, USER_IMAGE),
                DialogBox.getBotDialog(response, BOT_IMAGE)
        );
        userInput.clear();
        assert userInput.getText().length() == 0 : "User input in GUI text field is supposed to be cleared";
    }
}
