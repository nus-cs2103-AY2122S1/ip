package duke;

import command.Command;
import exception.DukeException;
import exception.ErrorAccessingFileException;
import javafx.application.Platform;
import message.ErrorMessage;
import message.Message;
import parser.Parser;
import storage.Storage;
import storage.StorageFile;
import tasklist.TaskList;

/**
 * Encapsulates a chatbot that greets the user,
 * adds valid inputs to a task list,
 * updates tasks in the list,
 * and exits when the user types `bye`.
 */
public class Duke {
    private TaskList list;
    private Parser parser;
    private ErrorMessage loadErrorMessage;

    /**
     * Instantiates a `Duke` chat bot.
     */
    public Duke() {
        try {
            Storage storage = new Storage();
            StorageFile storageFile = storage.loadListFile();
            TaskList list = storageFile.scanFileDataToNewTaskList();
            this.list = list;
        } catch (ErrorAccessingFileException e) {
            this.loadErrorMessage = e.getOutputMessage();
        }

        this.parser = new Parser();
    }

    /**
     * Gets response when user sends a message.
     *
     * @param input Input by user.
     * @return String response from Duke.
     */
    public String getResponse(String input) {
        try {
            if (this.parser.detectExitCommand(input)) {
                Platform.exit();
            }

            Command command = this.parser.createCommand(input);
            command.execute(this.list);
            return command.getOutputMessage().toString();
        } catch (DukeException e) {
            return e.getOutputMessage().toString();
        }
    }

    /**
     * Greets the user.
     *
     * @return Greeting string.
     */
    public String greet() {
        if (this.loadErrorMessage != null) {
            return this.loadErrorMessage.toString() + "\nPlease exit and try again later.";
        }

        Message greetingMessage = new Message("Hello! I'm Duke, what shall we do today?", "٩(｡•́‿•̀｡)۶");
        return greetingMessage.toString();
    }
}
