package duke;

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

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {

    private static final Font USER_FONT = Font.loadFont(DialogBox.class.getResource("/fonts/JetBrainsMono-Italic.ttf")
                    .toExternalForm(),
            15);
    private static final Image USER_IMAGE = new Image(MainWindow.class.getResourceAsStream("/images/UserIcon.jpg"));
    private static final Image BOT_IMAGE = new Image(MainWindow.class.getResourceAsStream("/images/BotIcon.png"));
    private static final Image SEND_IMAGE = new Image(MainWindow.class.getResourceAsStream("/images/SendIcon.png"));

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    @FXML
    private ImageView sendButtonImage;

    private Duke duke;

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * @param d Duke to be set as logical unit for the GUI.
     */
    public void setDuke(Duke d) {
        duke = d;
        dialogContainer.getChildren().addAll(DialogBox.getBotDialog(duke.runWelcome(), BOT_IMAGE));
        sendButtonImage.setImage(SEND_IMAGE);
        userInput.setFont(USER_FONT);
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();

        if (duke.isBye(input)) {
            Platform.exit();
            return;
        }

        String response = duke.run(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, USER_IMAGE),
                DialogBox.getBotDialog(response, BOT_IMAGE)
        );
        userInput.clear();
        assert userInput.getText().length() == 0 : "User input in GUI text field is supposed to be cleared";
    }
}
