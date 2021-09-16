package duke.ui;

import duke.Duke;
import duke.command.Command;
import duke.command.CommandExit;
import duke.command.CommandInvalid;
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

    private static final String USER_IMAGE_LOCATION = "/images/DaUser.png";
    private static final String DUKE_IMAGE_LOCATION = "/images/DaDuke.png";

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Duke duke;

    private Image userImage;
    private Image dukeImage;

    /**
     * Initialises the main window.
     */
    @FXML
    public void initialize() {
        assert this.getClass().getResourceAsStream(USER_IMAGE_LOCATION) != null : " User Image not found";
        assert this.getClass().getResourceAsStream(USER_IMAGE_LOCATION) != null : " Duke Image not found";

        userImage = new Image(this.getClass().getResourceAsStream(USER_IMAGE_LOCATION));
        dukeImage = new Image(this.getClass().getResourceAsStream(DUKE_IMAGE_LOCATION));

        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        startUp();
    }
    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        Command command = duke.getResponse(input);

        if (command instanceof CommandInvalid) {
            displayError(command.execute());
        } else if (command instanceof CommandExit) {
            displayResponse(input, command.execute());
        } else {
            displayResponse(input, command.execute());
        }
    }

    /**
     * Displays an error message should the command be invalid.
     *
     * @param displayText Text inputted, to be displayed.
     */
    private void displayError(String displayText) {
        dialogContainer
                .getChildren()
                .add(ErrorDialog
                        .getErrorDialog(displayText));
        userInput.clear();
    }

    /**
     * Displays response should the command be valid.
     *
     * @param input Text inputted.
     * @param displayText Text to display.
     */
    private void displayResponse(String input, String displayText) {
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(displayText, dukeImage)
        );
        userInput.clear();
    }

    /**
     * Displays the welcome message upon startup of the app.
     */
    private void startUp() {
        dialogContainer
                .getChildren()
                .add(DialogBox.getDukeDialog(Ui.introMessage(), dukeImage));
    }

}
