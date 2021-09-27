package duke.gui;

import duke.Duke;
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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.jpg"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/Duke.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Shows the user the AI's greeting.
     */
    public void greetUser() {
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(
            "Good Day! I'm The Terminator\nWhat can I do for you?\n\n" + getCommandList(), dukeImage));
    }

    private void byeUser() {
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(
            "Goodbye Human, I shall terminate the program", dukeImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input);
        if (response.equals("bye")) {
            byeUser();
            Main.closeDuke();
        } else {
            dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
            );
            userInput.clear();
        }
    }

    /**
     * Get a list of available commands.
     *
     * @return String of commands and descriptions.
     */
    public static String getCommandList() {
        String output = "Command List:\n"
            + "1. list -> Get a list of your tasks.\n"
            + "2. todo [task description] -> Add a todo task.\n"
            + "3. deadline [task description] /by [datetime] -> Add a deadline task with a deadline.\n"
            + "4. event [task description] /at [date] [start time]-[end time] -> Add an event task with a duration.\n"
            + "5. done [task number] -> Mark the task as done.\n"
            + "6. delete [task number] -> Delete the task.\n"
            + "7. clear -> Delete all existing tasks.\n"
            + "8. find [task description] -> Get a list of matching tasks.\n"
            + "9. commands -> Get a list of available commands.\n"
            + "10. bye -> Goodbye Human :)\n\n"
            + "You can also add /weekly or /daily "
            + "+ [no. of repetition] behind deadline task type to add recurring tasks.";
        return output;
    }
}
