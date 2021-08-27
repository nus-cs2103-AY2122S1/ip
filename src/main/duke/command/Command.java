package duke.command;

import duke.data.TaskList;
import duke.data.exception.DukeException;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * This class abstracts the Command that the user wants to execute.
 */
public abstract class Command {
    /**
     * Execute the command.
     *
     * @param tasks   The TaskList of the Duke instance.
     * @param ui      The UI handler of the Duke instance.
     * @param storage The storage handler of the Duke instance.
     * @throws DukeException The checked exception to be thrown when execution fails.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * Returns whether the Command is an ExitCommand.
     *
     * @return True if and only if the command is an ExitCommand.
     */
    public boolean isExit() {
        return false;
    }
}
