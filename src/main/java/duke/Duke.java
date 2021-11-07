package duke;

import duke.commands.Command;
import duke.exceptions.DukeException;
import duke.exceptions.DukeFileException;
import duke.gui.DukeDialogBox;
import duke.gui.DukeExceptionDialogBox;
import duke.gui.UserDialogBox;
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
    private static String dukeResponseHeader = "FullOfBugs:\n";
    private final Storage store;
    private final Ui ui;
    private TaskList taskList;
    private VBox dialogContainer;
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
            showDukeErrorMessage(e.getMessage());
            taskList = new TaskList();
        }
    }

    /**
     * Sets the dialogContainer field in Duke.
     *
     * @param dialogContainer A VBox instance of GUI.
     */
    public void setDialogContainer(VBox dialogContainer) {
        this.dialogContainer = dialogContainer;
        this.dialogContainer.getChildren().addAll(DukeDialogBox.getDukeDialog(ui.showWelcomeMessage(), dukeAvatar));
    }

    /**
     * Saves the current TaskList into a document.
     */
    public void saveTask() {
        try {
            this.taskList.safeTasks(store);
        } catch (DukeException e) {
            showDukeErrorMessage(e.getMessage());
        }
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container.
     *
     * @param input A String representing the input of user in the text box.
     */
    public void getResponse(String input) {
        showUserInput(input);
        try {
            Command command = Parser.decipher(input);
            String dukeMessage = command.execute(this.taskList, this.store, this.ui);
            showDukeResponse(dukeMessage);
        } catch (DukeException e) {
            showDukeErrorMessage(e.getMessage());
        }
    }

    private void showDukeErrorMessage(String error) {
        String dukeTextWhenException = dukeResponseHeader + error;
        dialogContainer.getChildren()
                .add(DukeExceptionDialogBox.getDukeExceptionDialog(dukeTextWhenException, dukeAvatarWhenException));
    }

    private void showDukeResponse(String message) {
        String dukeText = dukeResponseHeader + message;
        dialogContainer.getChildren().add(DukeDialogBox.getDukeDialog(dukeText, dukeAvatar));
    }

    private void showUserInput(String userInput) {
        dialogContainer.getChildren().add(UserDialogBox.getUserDialog(userInput, userAvatar));
    }

}
