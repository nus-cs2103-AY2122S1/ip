import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

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

    private final int FONT_SIZE = 13;
    private final int MILISECONDS_TO_SLEEP_BEFORE_QUITTING = 1000;

    private Tiger tiger = new Tiger();

    private final Image USER_IMAGE_LOCATION = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private final Image BOT_IMAGE_LOCATION = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Initialises the {@code javafx} window.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(
            DialogBox.getDukeDialog(this.tiger.start(), BOT_IMAGE_LOCATION)
        );
        Font font = Font.loadFont(MainWindow.class.getResource("/fonts/VictorMono-Medium.ttf").toExternalForm(),
                FONT_SIZE);
        sendButton.setFont(font);
        userInput.setFont(font);

    }

    public void setTiger(Tiger tiger) {
        this.tiger = tiger;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */

    private void respondToUser() {
        String input = userInput.getText();
        System.out.println(input);
        String response = this.tiger.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, USER_IMAGE_LOCATION),
                DialogBox.getDukeDialog(response, BOT_IMAGE_LOCATION)
        );
        userInput.clear();
    }

    private void checkExit() {
        new Thread(() -> {
            // The purpose of this line is to pause before the app exits, so the app does not instantly
            // exit once the user types bye.
            if (this.tiger.isExited()) {
                try {
                    Thread.sleep(MILISECONDS_TO_SLEEP_BEFORE_QUITTING);
                } catch (InterruptedException e) {
                    System.out.println(e.getStackTrace());
                }
                Platform.exit();
            }
        }).start();
    }

    @FXML
    private void handleUserInput() {
        respondToUser();
        Platform.runLater(() -> checkExit());
    }
}
