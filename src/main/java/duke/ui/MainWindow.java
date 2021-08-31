package duke.ui;

import duke.Duke;
import duke.DukeException;
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
    private static final String DUKE_PHOTO_URL =
            "https://upload.wikimedia.org/wikipedia/commons/thumb/5/5d/Duke_%28Java_mascot%29_waving.svg/"
            + "568px-Duke_%28Java_mascot%29_waving.svg.png";
    private static final String USER_PHOTO_URL =
            "https://image.flaticon.com/icons/png/512/1946/1946429.png";

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Duke duke;

    private final Image userImage = new Image(USER_PHOTO_URL);
    private final Image dukeImage = new Image(DUKE_PHOTO_URL);

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke d) {
        duke = d;
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(Ui.welcomeMessage(), dukeImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        dialogContainer.getChildren().add(DialogBox.getUserDialog(input, userImage));
        try {
            String response = duke.listen(input);
            dialogContainer.getChildren().add(
                    DialogBox.getDukeDialog(response, dukeImage));
        } catch (DukeException e) {
            dialogContainer.getChildren().add(DialogBox.getDukeDialog(e.getMessage(), dukeImage));
        }
        userInput.clear();
    }
}
