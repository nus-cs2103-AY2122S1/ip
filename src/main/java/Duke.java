import command.Command;
import exception.DukeException;
import storage.Storage;
import task.TaskList;


/**
 * Duke is secretary chatbot.
 * It stores tasks entered by users locally and reloads them on every start-up.
 */

public class Duke {

    private Storage storage;
    private TaskList tasks;

    /**
     * Creates the instance of Duke.
     * The path to the local file and tasklist will be initialised.
     *
     * @param filePath the path to the local file
     */

    public Duke(String filePath) {
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            tasks = new TaskList();
        }
    }


    protected String getResponse(String input) {
        try {
            Command c = Command.parse(input);
            return c.execute(tasks, storage);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
