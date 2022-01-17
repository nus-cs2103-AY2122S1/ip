package duke;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Duke duke;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/person.jpg"));
    private final Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/friend.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke duke) {
        this.duke = duke;
        dialogContainer.getChildren().add(ChatBox.getDukeDialog(duke.getUi().showWelcomeMessage(), dukeImage));
        dialogContainer.getChildren().add(ChatBox.getDukeDialog(duke.loadListGui(), dukeImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.parseGui(input, duke);
        assert !response.equals("") : "there should be a response to the command";
        dialogContainer.getChildren().addAll(
            ChatBox.getUserDialog(input, userImage),
            ChatBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }
}
