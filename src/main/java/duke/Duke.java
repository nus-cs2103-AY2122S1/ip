package duke;

import command.Command;
import exception.DukeException;
import javafx.application.Platform;
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

    /**
     * Instantiates a `Duke` chat bot.
     */
    public Duke() {
        StorageFile storageFile = Storage.loadListFile();
        TaskList list = Storage.scanListFileDataToList(storageFile);

        this.list = list;
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
}
