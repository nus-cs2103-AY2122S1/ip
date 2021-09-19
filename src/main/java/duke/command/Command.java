package duke.command;

import duke.Storage;
import duke.UI;
import duke.error.DukeException;
import duke.task.TaskList;

/**
 * Represents a command object.
 */
public abstract class Command {
    /**
     * Executes the command
     *
     * @param tasks List of tasks.
     * @param ui UI object.
     * @param storage Storage object.
     * @return The execution result.
     * @throws DukeException If any errors occur.
     */
    public abstract String execute(TaskList tasks, UI ui, Storage storage) throws DukeException;
}
