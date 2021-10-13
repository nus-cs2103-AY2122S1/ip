package duke.gui;

import duke.processor.Duke;
import duke.exception.DukeExcpetion;
import duke.processor.Parser;
import duke.processor.Ui;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 * Resuing code from SE-EDU javaFX tutorial.
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

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        botSays(Ui.greet());
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Returns the result of duke according to user input.
     *
     * @param userInput string of user input
     * @return the response of duke
     */
    public String getOutputString(String userInput) {
        try {
            return Parser.parse(userInput, duke.getTaskList()).execute(duke.getTaskList());
        } catch (DukeExcpetion e) {
            return e.toString();
        }
    }

    /**
     * Creates two dialog boxes, one displaying user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = getOutputString(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }

    @FXML
    public void botSays(String response) {
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(response, dukeImage)
        );
    }
}