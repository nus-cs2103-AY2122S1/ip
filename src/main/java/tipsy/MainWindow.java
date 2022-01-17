package tipsy;

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

    private Tipsy tipsy;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/Images/User_DP.png"));
    // Image taken from https://myanimelist.net/character/103091/Tippy/pics
    private Image tipsyImage = new Image(this.getClass().getResourceAsStream("/Images/Tipsy_DP.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setTipsy(Tipsy t) {
        tipsy = t;
        dialogContainer.getChildren().add(
                DialogBox.getTipsyDialog(t.getStartUpMessage(), tipsyImage));

        if (!t.getTasksLoadedMessage().equals("")) {
            dialogContainer.getChildren().add(
                    DialogBox.getTipsyDialog(t.getTasksLoadedMessage(), tipsyImage));
        }
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Tipsy's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = tipsy.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getTipsyDialog(response, tipsyImage)
        );
        userInput.clear();
    }
}
