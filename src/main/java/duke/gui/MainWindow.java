package duke.gui;

import duke.Duke;
import duke.DukeException;
import duke.Ui;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * A class to handle all the GUI/JavaFX portion of Duke
 */
public class MainWindow extends AnchorPane {
    @FXML
    private VBox dialogContainer;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    @FXML
    private Duke duke;

    /**
     * Initialises this MainWindow for JavaFX
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        this.dialogContainer.getChildren().add(new Label(Ui.showWelcomeMessage()));
    }

    /**
     * Sets duke for MainWindow.
     *
     * @param d Duke object to initialise
     */
    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Handles the text input by the user.
     */
    @FXML
    private void handleUserInput() {
        DialogBox userText = DialogBox.getUserDialog(this.userInput.getText());
        DialogBox dukeText;
        String inputString = this.userInput.getText();
        String parsedString = "";

        if (inputString.isBlank()) {
            return;
        }

        try {
            parsedString = this.duke.handleInput(inputString);
            dukeText = DialogBox.getDukeDialog(Ui.showReply(parsedString));
        } catch (DukeException e) {
            dukeText = DialogBox.getErrorDialog(Ui.showErrorMessage(e));
        }

        this.dialogContainer.getChildren().addAll(userText, dukeText);
        this.duke.saveDataToFile();
        this.userInput.clear();

        if (parsedString.equals("bye")) {
            System.exit(0);
        }
    }
}
