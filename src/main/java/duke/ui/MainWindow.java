package duke.ui;

import duke.Duke;
import duke.exception.DukeException;
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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));
    private Image dukeShockImage = new Image(this.getClass().getResourceAsStream("/images/DaDukeShocked.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke d) {
        duke = d;
        String startUpMessage = duke.startUp();
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(startUpMessage, dukeImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        DialogBox inputDialogBox = DialogBox.getUserDialog(input, userImage);
        DialogBox responseDialogBox = null;

        String response;
        try {
            response = duke.getResponse(input);
            responseDialogBox = DialogBox.getDukeDialog(response, dukeImage);
        } catch (DukeException e) {
            Ui ui = new Ui();
            response = ui.displayDukeExceptionMessage(e);
            responseDialogBox = DialogBox.getDukeDialog(response + "\nYour input is: " + input,
                    dukeShockImage);
        }
        dialogContainer.getChildren().addAll(inputDialogBox, responseDialogBox);
        userInput.clear();

        if (input.equalsIgnoreCase("bye")) {
            Stage stage = (Stage) dialogContainer.getScene().getWindow();
            stage.close();
        }
    }
}
