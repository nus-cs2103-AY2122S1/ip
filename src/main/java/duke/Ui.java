package duke;

import java.util.Timer;
import java.util.TimerTask;

import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * This class encapsulates the UI of Duke and its interactions with the user.
 *
 * @author Kleon Ang
 */
public class Ui extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;

    /**
     * Initializes the main Ui window.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response;
        try {
            response = Duke.getResponse(userInput.getText());
        } catch (DukeException e) {
            response = e.getMessage();
        }
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input),
                DialogBox.getDukeDialog(response)
        );
        userInput.clear();
        // Exits after 3 seconds upon receiving bye response
        if (response.equals("Bye. Hope to see you again soon!")) {
            new Timer().schedule(new TimerTask() {
                public void run () {
                    System.exit(0);
                }
            }, 3000);
        }
    }

    /**
     * Prints the given string formatted in a reply.
     *
     * @param string The string to format in a reply.
     */
    public void printReply(String string) {
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(string));
    }

    /**
     * Prints an error message formatted in a reply for an input file name that does not exist.
     *
     * @param fileName The name of the file that does not exist.
     */
    public void showLoadingError(String fileName) {
        printReply(fileName + " not found. File has been created.");
    }

    /**
     * Prints a success message formatted in a reply for an input file name that does not exist.
     *
     * @param fileName The name of the file that does not exist.
     */
    public void showLoadingSuccess(String fileName) {
        printReply("I found a " + fileName + " file!\nYour tasks have been imported.");
    }
}
