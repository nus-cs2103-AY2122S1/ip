package duke.command;

import duke.Storage;
import duke.UI;
import duke.error.DukeException;
import duke.task.TaskList;

/**
 * Represents a command object.
 */
public abstract class Command {
    boolean isExit;

    /**
     * Executes the command
     *
     * @param tasks List of tasks.
     * @param ui UI object.
     * @param storage Storage object.
     * @throws DukeException If any errors occur.
     */
    public abstract void execute(TaskList tasks, UI ui, Storage storage) throws DukeException;

    /**
     * Check whether this command is an exit command.
     *
     * @return True if the command is an exit command. Otherwise, returns false.
     */
    public boolean isExitCommand() {
        return isExit;
    }
}
