import javafx.application.Platform;
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

    private Tiger tiger = new Tiger();

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(
            DialogBox.getDukeDialog(this.tiger.start(), dukeImage)
        );

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
        String response = this.tiger.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }

    private void checkExit() {
        new Thread(
                () -> {
                    // this whole thing is to show the last command before the user exits, so the app does not instantly
                    // exit once the user types bye.
                    if (this.tiger.isExited()) {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            System.out.println(e.getStackTrace());
                        }
                        Platform.exit();
                    }
                }
        ).start();
    }

    @FXML
    private void handleUserInput() {
        respondToUser();
        Platform.runLater(() -> checkExit());
    }
}