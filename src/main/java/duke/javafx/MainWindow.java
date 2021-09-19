package duke.javafx;

import java.util.Objects;

import duke.Duke;
import duke.Ui;
import duke.exception.DukeException;
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
    private final Image userImage = new Image(
            Objects.requireNonNull(this.getClass().getResourceAsStream("/images/DaUser.png"))
    );
    private final Image dukeImage = new Image(
            Objects.requireNonNull(this.getClass().getResourceAsStream("/images/DaDuke.png"))
    );
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    private Duke duke;

    /**
     * Initialize the main window.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        String greet = Ui.greet();
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(greet, dukeImage, false)
        );
        userInput.setPromptText("Message...");
    }

    /**
     * Creates a dialog box with the exception message.
     */
    @FXML
    public void showException(Exception e) {
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(e.getMessage(), dukeImage, true)
        );
    }

    public void setDuke() {
        try {
            duke = new Duke();
        } catch (DukeException e) {
            showException(e);
        }
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String[] response = duke.getResponse(input);
        String output = response[0];
        boolean isWarning = response[1].equals("F");
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage, isWarning),
                DialogBox.getDukeDialog(output, dukeImage, isWarning)
        );
        userInput.clear();
    }
}
