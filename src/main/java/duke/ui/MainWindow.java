package duke.ui;

import duke.Duke;
import duke.response.DukeBadResponse;
import duke.response.DukeResponse;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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

    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image dukeHappyImage = new Image(this.getClass().getResourceAsStream("/images/qoobee.png"));
    private Image dukeScaredImage = new Image(this.getClass().getResourceAsStream("/images/qoobeeScared.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke d) {
        duke = d;
        DukeResponse startUpResponse = duke.greet();
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(startUpResponse.toString(), dukeHappyImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        DukeResponse response = duke.getResponse(input);
        Image dukeImage = response instanceof DukeBadResponse ? dukeScaredImage : dukeHappyImage;
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response.toString(), dukeImage)
        );
        userInput.clear();
        if (response.isExit()) {
            Stage stage = (Stage) dialogContainer.getScene().getWindow();
            stage.close();
        }
    }
}
