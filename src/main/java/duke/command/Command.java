package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Abstract Command class that executes the command of the user.
 */
public abstract class Command {

    /**
     * Executes itself.
     *
     * @param taskList The TaskList of Duke.
     * @param ui The Ui of Duke.
     * @param storage The Storage of Duke.
     * @throws DukeException  If error occur during execution.
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;

    /**
     * Checks if command is an ExitCommand.
     *
     * @return Whether command is an ExitCommand.
     */
    public boolean isExit() {
        return this instanceof ExitCommand;
    }
}
