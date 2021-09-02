package duke.command;

import duke.data.TaskHandler;
import duke.data.exception.DukeException;
import duke.storage.Storage;

/**
 * Class that represents an executable command.
 *
 * @author Won Ye Ji
 */
public abstract class Command {
    protected TaskHandler taskHandler;
    protected Storage storage;

    /**
     * Constructor for Command.
     *
     * @param th Task Handler that handles the operation.
     * @param str Storage that holds the task information.
     */
    public Command(TaskHandler th, Storage str) {
        this.taskHandler = th;
        this.storage = str;
    }

    /**
     * Executes the Command.
     *
     * @param cmd Command string to be executed.
     * @return Duke's response to the user.
     * @throws DukeException if task faces an error during execution.
     */
    public abstract String execute(String cmd) throws DukeException;
}
