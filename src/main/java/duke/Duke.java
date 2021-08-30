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
    private final Storage store;
    private final Ui ui;
    private TaskList taskList;

    private final Image userAvatar = new Image(this.getClass().getResourceAsStream("/images/UserAvatar.png"));
    private final Image dukeAvatar = new Image(this.getClass().getResourceAsStream("/images/DukeAvatar.png"));
    private final Image dukeAvatarWhenException = new Image(this.getClass()
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
            System.out.println(e.getMessage());
            taskList = new TaskList();
        }
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container.
     *
     * @param input A String representing the input of user in the text box.
     * @param dialogContainer A VBox instance on main window.
     * @return A boolean representing if duke is shutting down.
     */
    public boolean getResponse(String input, VBox dialogContainer) {
        try {
            Command command = Parser.decipher(input);
            String output = command.execute(this.taskList, this.store, this.ui);

            String dukeText = "FullOfBugs:\n" + output;
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userAvatar),
                    DialogBox.getDukeDialog(dukeText, dukeAvatar)
            );
            return command.isExit();
        } catch (DukeException e) {
            String dukeTextWhenException = "FullOfBugs:\n" + e.getMessage();
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userAvatar),
                    DialogBox.getDukeDialog(dukeTextWhenException, dukeAvatarWhenException)
            );
            return false;
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

}
