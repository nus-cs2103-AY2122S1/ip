package duke;

import duke.command.Command;
import duke.task.TaskList;
import duke.util.DukeException;
import duke.util.Parser;
import duke.util.Storage;

/**
 * A personal assistance bot that tracks Events, Dealines, and todos
 * which can be completed or deleted. A user sends instructions to Duke
 * by typing command sentences.
 */
public class Duke {
    protected Storage storage;
    protected TaskList taskList;

    /**
     * Initialises storage, task list and ui instances to be used
     * when duke is running.
     */
    public Duke() {
        storage = new Storage();
        taskList = new TaskList();
    }

    /**
     * Gets a duke command based on user input message.
     * @param input user instructions for duke in form of string.
     *
     * @return a command depending on user input
     */
    Command getCommand(String input) throws DukeException {
        return Parser.parseInputs(input);
    }

    /**
     * Greets user once and attempt to load data from local storage.
     * and response printed according to user inputs.
     *
     * @return A string to indicate if duke has been initialised.
     */
    protected String initDuke() {
        try {
            taskList.readFile(storage.loadDataFile());
            return "Im Mr Meeseeks! \n LOOK AT ME!";
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
