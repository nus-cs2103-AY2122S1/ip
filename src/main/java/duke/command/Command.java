package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskManager;
import duke.ui.Ui;

/**
 * Represents a command entered by the user.
 */
public abstract class Command {
    /**
     * Executes the command.
     * @param taskManager The <code>TaskManager</code> responsible for keeping track of the existing tasks.
     * @param ui The <code>Ui</code> used for displaying output messages to the user.
     * @param storage The <code>Storage</code> used to save updated task data.
     * @throws DukeException if any errors occur during execution
     */
    public abstract void execute(TaskManager taskManager, Ui ui, Storage storage) throws DukeException;

    /**
     * Signal for whether the application should exit.
     * @return true if the command should tell the application to exit
     */
    public abstract boolean isExit();
}
