package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.TaskList;

/**
 * Represents a command that the Duke program can execute.
 *
 * @author ruiquan
 */
public abstract class Command {
    private final boolean isExit;

    /**
     * Constructs a Command.
     *
     * @param isExit Whether the command is an exit command.
     */
    public Command(boolean isExit) {
        this.isExit = isExit;
    }

    /**
     * Informs caller if the command is an exit command.
     *
     * @return True if the command is an exit command, false otherwise.
     */
    public boolean isExit() {
        return isExit;
    }

    /**
     * Executes the command and returns the string message representing
     * the response.
     *
     * @param tasks The collection of tasks.
     * @param storage The storage manager that deals with loading from and
     *                saving into a file.
     * @return The message representing the response.
     * @throws DukeException If there is an error.
     */
    public abstract String execute(TaskList tasks, Storage storage) throws DukeException;
}
