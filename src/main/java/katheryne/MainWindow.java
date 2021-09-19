package katheryne;

import static java.lang.Thread.interrupted;
import static java.lang.Thread.sleep;

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

    private Katheryne katheryne;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/aether_default.png"));
    private Image katheryneImage = new Image(this.getClass().getResourceAsStream("/images/katheryne_default.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setKatheryne(Katheryne k) {
        katheryne = k;
        dialogContainer.getChildren().add(
                DialogBox.getKatheryneDialog(Message.greet(katheryne.lst), katheryneImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = katheryne.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getKatheryneDialog(response, katheryneImage)
        );
        userInput.clear();
        if (response.equals(Message.goodbye())) {
            try {
                sleep(400);
            } catch (InterruptedException e) {
                dialogContainer.getChildren().addAll(
                        DialogBox.getKatheryneDialog("That was interrupted. What were you saying?", katheryneImage)
                );
            } finally {
                Platform.exit();
            }
        }
    }
    
}