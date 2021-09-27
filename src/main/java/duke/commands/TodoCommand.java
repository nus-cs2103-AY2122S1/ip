package duke.commands;

import duke.data.exception.DukeException;
import duke.data.task.Todo;

/**
 * Encapsulates the Todo command's operations
 */
public class TodoCommand extends AddCommand {
    /**
     * Constructor for TodoCommand
     *
     * @param rest the user input after the command
     * @throws DukeException if user input invalid or empty
     */
    public TodoCommand(String rest) throws DukeException {
        super(new Todo(rest));
    }
}
