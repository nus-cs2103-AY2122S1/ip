import duke.Parser;
import duke.Storage;
import duke.TaskList;
import duke.command.Command;
import duke.exception.DukeException;

/**
 * The main class for the Duke program, which manages tasks for the user.
 *
 * @author nhjryan
 */
public class Duke {

    private static final String FILE_PATH = "data/tasks.txt";
    private Storage storage;
    private TaskList tasks;


    /**
     * The constructor for a Duke object.
     *
     */
    public Duke() {
        storage = new Storage(FILE_PATH);

        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            tasks = new TaskList();
        }
    }

    /**
     * Takes in the input of the user and returns the relevant
     * response message.
     *
     * @param input User input.
     * @return The relevant response message.
     */
    String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, storage);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

}
