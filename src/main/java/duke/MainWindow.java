package duke;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller that manages layout of the main chat.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private VBox dialogContainer;
    private Image admin = new Image(MainWindow.class.getResourceAsStream("/images/duke.png"));
    private Image user = new Image(MainWindow.class.getResourceAsStream("/images/user.png"));
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    private Duke duke;
    /**
     * Initialize Main Window.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        scrollPane.setFitToWidth(true);
        dukeResponse("Hello! I'm Duke. What can I do for you?");
    }

    /**
     * Set Duke.
     *
     * @param duke Duke.
     */
    public void setDuke(Duke duke) {
        this.duke = duke;
    }
    @FXML
    private void handleUserInput() {
        String input = userInput.getText().trim();
        if (!input.isEmpty()) {
            dialogContainer.getChildren()
                    .addAll(DialogBox.getUserDialog(input, user, 0),
                            DialogBox.getUserDialog(duke.run(input), admin, 1));
        }
        userInput.clear();
    }

    @FXML
    private void dukeResponse(String response) {
        dialogContainer.getChildren().add(DialogBox.getUserDialog(response, admin, 1));
    }
}
