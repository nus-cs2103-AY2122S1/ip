package duke;

import duke.commands.Command;
import duke.exceptions.DukeException;
import duke.exceptions.DukeFileException;
import duke.gui.DialogBox;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

/**
 * This is the Duke class that interacts with users using inputs from users in CLI.
 */
public class Duke {

    /**
     * The following are private class fields of a Duke instance.
     */
    private Storage store;
    private Ui ui;
    private TaskList taskList;

    private Image userAvatar = new Image(this.getClass().getResourceAsStream("/images/UserAvatar.png"));
    private Image dukeAvatar = new Image(this.getClass().getResourceAsStream("/images/DukeAvatar.png"));
    private Image dukeAvatarWhenException = new Image(this.getClass()
            .getResourceAsStream("/images/DukeAvatar_Exceptions.png"));

    /**
     * This is the Constructor of Duke.
     */
    public Duke() {
        java.nio.file.Path filePath = java.nio.file.Paths.get("src",
                "main", "java", "data", "StoredData.txt");
        ui = new Ui();
        this.store = new Storage(filePath.toString());
        try {
            taskList = new TaskList(store.load());
        } catch (DukeFileException e) {
            ui.showError(e.getMessage());
            taskList = new TaskList();
        }
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput(String input, VBox dialogContainer) {
        try {
            Command command = Parser.decipher(input);
            String output = command.execute(this.taskList, this.store, this.ui);

            String dukeText = "FullOfBugs:\n" + output;
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userAvatar),
                    DialogBox.getDukeDialog(dukeText, dukeAvatar)
            );
        } catch (DukeException e) {
            String dukeTextWhenException = "FullOfBugs:\n" + e.getMessage();
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userAvatar),
                    DialogBox.getDukeDialog(dukeTextWhenException, dukeAvatarWhenException)
            );
        }
    }

    /**
     * Welcomes the user upon entering the GUI.
     *
     * @return A DialogBox instance with Welcome message.
     */
    public DialogBox welcomeUser() {
        return DialogBox.getDukeDialog(ui.showWelcomeMessage(), dukeAvatar);
    }

    /**
     * Appends Duke's message with its name to show as a chat.
     *
     * @param input  A String representing the message print out by Duke.
     * @param dialogContainer A VBox instance that deals with
     */
    public void getResponse(String input, VBox dialogContainer) {
        this.handleUserInput(input, dialogContainer);
    }
}
