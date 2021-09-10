package duke.command;

import duke.Duke;
import duke.Storage;
import duke.exception.DukeException;
import duke.task.TaskList;

/**
 * Command class encapsulates the command input by user.
 */
public abstract class Command {
    /**
     * @param tasks   Task list used to store the tasks.
     * @param storage Storage used to deal with making sense of the user command.
     * @throws DukeException If the command is erroneous.
     */
    // TODO: let UI class handle shouldPrintMessage logic
    public abstract void execute(Duke duke, TaskList tasks, Storage storage)
        throws DukeException;

    /**
     * Returns true if this command is an ExitCommand.
     *
     * @return true if this command is an ExitCommand.
     */
    public boolean isExit() {
        return this instanceof ExitCommand;
    }
}
