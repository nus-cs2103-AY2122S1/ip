import duke.Command;
import duke.DukeException;
import duke.Parser;
import duke.Storage;
import duke.TaskList;

/**
 * Duke Class to read input commands and accordingly create a list of task.
 */
public class Duke {

    private final TaskList tasks;
    private final Storage storage;
    private final Parser parser;

    /**
     * Constructor for the Duke class
     */
    public Duke() {
        storage = new Storage("data/duke.txt");
        tasks = new TaskList(storage.load());
        parser = new Parser();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        try {
            Command command = parser.parse(input);
            return command.execute(tasks, storage);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
