package duke.app;

import duke.commands.Command;
import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.DukeException;
import duke.utils.Parser;

/**
 * The main program
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;

    /**
     * Constructor. Initializes the database and tasklists
     *
     * @param filePath  takes in filepath to for the database(.txt)
     */
    public Duke(String filePath) {
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            tasks = new TaskList();
        }
    }

    /**
     * executes an input and returns a response from duke
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, storage);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}


