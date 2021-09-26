package duke.controller;

import duke.Duke;
import duke.exception.DukeException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * The MainWindow class is a controller class that provides the layout for the other controls.
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
    @FXML
    private Button helpButton;

    private Duke duke;

    /**
     * Initializes the controller.
     */
    @FXML
    public void initialize() {
        try {
            scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
            duke = new Duke();
        } catch (DukeException e) {
            dialogContainer.getChildren().add(DialogBox.getDukeDialog(e.toString()));
        } finally {
            dialogContainer.getChildren().add(DialogBox.getDukeDialog(duke.getStartMessage()));
        }
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply,
     * then appends them to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        dialogContainer.getChildren().add(DialogBox.getUserDialog(input));
        String response = duke.getResponse(input);
        userInput.clear();
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(response));
        if (input.equals("bye")) {
            userInput.setEditable(false);
            sendButton.setDisable(true);
            helpButton.setDisable(true);
        } else if (input.equals("help")) {
            dialogContainer.getChildren().add(new HelpMenu(duke, dialogContainer));
        }
    }

    @FXML
    private void handleHelpButtonPress() {
        String response = duke.getResponse("help");
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(response));
        dialogContainer.getChildren().add(new HelpMenu(duke, dialogContainer));
    }
}
