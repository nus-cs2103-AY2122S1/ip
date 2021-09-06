package duke.controller;

import duke.Duke;
import duke.GUI;
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

    /**
     * Initialises the main window.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        // Display greeting upon initialisation
        dialogContainer.getChildren().add(
                DialogBox.getOpeningMessage(displayGreeting(), dukeImage));
    }

    /**
     * Sets a Duke object as the duke chatbot.
     * @param d duke object.
     */
    public void setDuke(Duke d) {
        duke = d;
        d.openDukeChatBot();
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText().strip();
        String response = " ";

        DialogBox userDB = null;
        DialogBox dukeDB = null;

        try {
            response = this.getResponse(input);
            userDB = DialogBox.getUserDialog(input, userImage);
            dukeDB = DialogBox.getDukeDialog(response, dukeImage);
        } catch (DukeException e) {
            System.out.println("here");
            dukeDB.modifyColorForError();
        }

        dialogContainer.getChildren().addAll(
                userDB,
                dukeDB
        );

        userInput.clear();
    }

    /**
     * Returns the response from Duke based on the input from the user.
     * @param input user input from keying into the TextField.
     * @return response from Duke.
     */
    private String getResponse(String input) {
        String toReturn = "";
        try {
            toReturn = duke.getResponse(input);
        } catch (DukeException e) {
            toReturn = GUI.sendErrorMessage(e);
            throw e;
        } finally {
            return toReturn;
        }
    }

    /**
     * Displays the greeting message when app is initialised.
     * @return opening message.
     */
    private String displayGreeting() {
        return Duke.getGreeting();
    }
}
