package duke;

import command.Command;
import exception.DukeException;
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
        // Load data
        StorageFile storageFile = Storage.loadListFile();

        // Scan data to a list
        TaskList list = Storage.scanListFileDataToList(storageFile);
        this.list = list;

        this.parser = new Parser();
    }

    public String getResponse(String input) {
        try {
            Command command = this.parser.makeCommand(input);
            command.execute(this.list);
            return command.getOutputMessage().toString();
        } catch (DukeException e) {
            return e.getOutputMessage().toString();
        }
    }
}
