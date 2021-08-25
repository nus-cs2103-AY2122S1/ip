package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.TaskList;

/**
 * Command class encapsulates the command input by user.
 */
public abstract class Command {
    /**
     * @param tasks   Task list used to store the tasks.
     * @param ui      Ui used to deal with interactions with the user.
     * @param storage Storage used to deal with making sense of the user command.
     * @throws DukeException If the command is erroneous.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * Returns true if this command is an ExitCommand.
     *
     * @return true if this command is an ExitCommand.
     */
    public Boolean isExit() {
        return this instanceof ExitCommand;
    }
}
