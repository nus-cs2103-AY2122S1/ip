package pats.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import pats.Pats;

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

    private Pats pats;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/user_icon.png"));
    private final Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/pats_icon1.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Pats d) {
        pats = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Pats's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        if (!pats.isExit()) {
            String input = userInput.getText();
            String response = pats.getResponse(input);
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getDukeDialog(response, dukeImage)
            );
            userInput.clear();
        }
    }

    protected void printWelcomeMessage(String s) {
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(s, dukeImage));
    }
}
