/**
 * This function is responsible for the Main Window of the GUI.
 *
 * @author Megan Wee Rui En
 * @version CS2103T AY21/22 Semester 1
 */

package duke.ui;

import java.util.Objects;

import duke.Duke;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    private Duke duke;

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;

    private final Image userImage = new Image(Objects.requireNonNull(
            this.getClass().getResourceAsStream("/images/DaUser.png")));
    private final Image dukeImage = new Image(Objects.requireNonNull(
            this.getClass().getResourceAsStream("/images/DaDuke.png")));

    /**
     * Initialises a dialog container.
     */
    @FXML
    public void initialize() {
        Ui ui = new Ui();
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(ui.showWelcome(), dukeImage)
        );
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Assigns parameter d to the duke attribute to param d.
     *
     * @param d The duke object the attribute duke will be assigned to.
     */
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
        assert input != null : "The user input cannot be null.";
        String response = duke.getResponse(input);

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }
}

