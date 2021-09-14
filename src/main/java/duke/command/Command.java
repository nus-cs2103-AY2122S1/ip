package duke.command;

import java.time.DateTimeException;

import duke.DukeException;
import duke.TaskManager;

/**
 * Represents a command that can be executed.
 */
public abstract class Command {
    /**
     * Executes the command, modify given taskManager and return output String.
     *
     * @param taskManager Instance of TaskManager that handles tasks.
     * @return output string
     * @throws DukeException If something goes wrong with the Duke command.
     */
    public abstract String execute(TaskManager taskManager)
            throws DukeException, IllegalArgumentException, DateTimeException;

    public boolean isExit() {
        return false;
    }
}
