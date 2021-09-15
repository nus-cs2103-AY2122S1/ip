package duke;

import duke.commands.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Allows users to add 3 different types of tasks, mark them as done, and delete tasks.
 *
 * The commands for usage are as follows:
 * 1. "todo name" where name is what the user would like the todo to be called.
 * 2. "event name /at YYYY-MM-DD"
 * 3. "deadline name /by YYYY-MM-DD"
 * 4. "list" to view current tasks added to the tasks list.
 * 5. "done taskNumber" to mark task as completed.
 * 6. "delete taskNumber" to delete task.
 * 7. "bye" to leave the ChatBot.
 * Disclaimer: any other commands will not be recognised and user will be prompted to enter a valid command.
 *
 * @author Leong Hong Fai
 */

public class Duke {
    private static final String fileAddress = "data/duke.txt";
    private static Storage storage;
    private static TaskList tasks;

    /**
     * Creates a Duke object.
     */
    public Duke() {
        this.storage = new Storage(fileAddress);
        try {
            this.tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            this.tasks = new TaskList();
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     *
     * @return String array of responses.
     */
    public String[] getResponse(String input) {
        return commands(input);
    }

    /**
     * Facilitates which command to run based on the type of command processed by the Parser.
     *
     * @throws DukeException If command is not recognised or improperly formatted.
     * @return String array of results of running the command.
     */
    private String[] commands(String command) throws DukeException {
        try {
            Command c = Parser.process(command);
            return c.execute(storage, tasks);
        } catch (DukeException e) {
            return Ui.display(e.toString());
        }

    }
}





