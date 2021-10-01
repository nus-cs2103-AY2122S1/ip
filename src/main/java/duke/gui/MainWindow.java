package duke.gui;

import duke.Duke;
import duke.exceptions.DukeException;
import duke.exceptions.DukeExitException;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

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

    /**
     * Initialize the window with welcome dialog.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        displayWelcomeDialog();
    }

    /**
     * Sets the duke backend to use for this front end instance.
     *
     * @param d Duke backend object to use
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
        try {
            String response = duke.getResponse(input);
            makeDialogBox(input, response);
        } catch (DukeExitException e) {
            String response = "Bye bye!";
            makeDialogBoxError(input, response);
            Platform.exit();
        } catch (DukeException e) {
            makeDialogBoxError(input, e.getMessage());
        }

        userInput.clear();
    }

    /**
     * Format user dialog and duke response.
     *
     * @param input    user input String
     * @param response duke response String
     */
    private void makeDialogBox(String input, String response) {
        DialogBox userBox = DialogBox.getUserDialog("User", input);
        DialogBox dukeBox = DialogBox.getDukeDialog("Duke", response);
        dialogContainer.getChildren().addAll(userBox, dukeBox);
    }

    private void makeDialogBoxError(String input, String response) {
        DialogBox userBox = DialogBox.getUserDialog("User", input);
        DialogBox dukeBox = DialogBox.getDukeDialogError("Duke", response);
        dialogContainer.getChildren().addAll(userBox, dukeBox);
    }

    private void displayWelcomeDialog() {
        String welcomeMessage = "Welcome to Duke\nType list to see all current tasks\nType help for help";
        DialogBox dukeBox = DialogBox.getDukeDialog("Duke", welcomeMessage);
        dialogContainer.getChildren().addAll(dukeBox);
    }
}
