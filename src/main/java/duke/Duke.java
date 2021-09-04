package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * The Duke program implements a bot with a set of simple commands
 * Initializes the app, and interacts with the user.
 */
public class Duke {

    /**
     * The tasks associated with the user
     **/
    private TaskList tasks;

    /**
     * The storage location associated with the user
     **/
    private Storage storage;

    /**
     * The ui interacting with the user
     **/
    private Ui ui;

    /**
     * The default path used when using the empty constructor.
     */
    private static final String DEFAULT_PATH = "./data/tasks.txt";

    /**
     * Initializes the duke program with a given filePath.
     *
     * @param filePath The path of the file containing your list of tasks.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);

    }

    /**
     * Load tasks, returning the appropriate message.
     *
     * @return A successful message, if task list is found, else return the error message.
     */
    public String loadTasks() {
        try {
            tasks = new TaskList(storage.load());
            assert (tasks != null) : "Task list not initialized correctly";
            return "I have loaded up your tasks successfully.";
        } catch (DukeException e) {
            tasks = new TaskList();
            return ui.showError(e.getMessage());
        }
    }

    /**
     * Empty constructor for Duke.
     */
    public Duke() {
        this(DEFAULT_PATH);
    }

    /**
     * Generates a response to user input.
     *
     * @param input The String representing the user input.
     * @return A string in response to the user input.
     */
    public String getResponse(String input) {
        try {
            assert (storage != null) : "No storage initialized";
            Command c = Parser.parse(input);
            String botResponse = c.execute(tasks, ui, storage);
            storage.write(tasks);
            return botResponse;
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

}
