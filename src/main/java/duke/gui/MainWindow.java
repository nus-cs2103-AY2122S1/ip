package duke.gui;

import static duke.util.Ui.EXIT_MESSAGE;

import duke.Duke;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Represents the controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;

    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Initialises the dialogContainer and scrollPane.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Sets the MainWindow object to the correct instance of Duke.
     *
     * @param duke The appropriate instance of duke.
     */
    public void setDuke(Duke duke) {
        this.duke = duke;
    }

    /**
     * Welcomes the user when he/she first logs in.
     */
    public void welcomeuser() {
        DukeDialogBox.welcomeUser(dialogContainer, dukeImage);
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText().trim();
        String response = duke.getResponse(input);

        if (input.equals("")) {
            return;
        }

        // Idea and code from alissayarmantho's issue
        if (response.equals(EXIT_MESSAGE)) {
            duke.closeScanner();
            Platform.exit();
        }

        /* Alternates between user and Duke dialog boxes. */
        dialogContainer.getChildren().addAll(
                UserDialogBox.getDialog(input, userImage),
                DukeDialogBox.getDialog(response, dukeImage)
        );
        userInput.clear();
    }
}
