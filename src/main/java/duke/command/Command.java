package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * This abstract Command class represents an executable command.
 */
public abstract class Command {

    /**
     * Executes the command.
     *
     * @param tasks The task list.
     * @param ui The UI of the application.
     * @param storage The storage system of the application.
     * @throws DukeException If the command cannot be executed.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * Indicates if the user enters a command to exit the system.
     *
     * @return A boolean value indicating the exit status of the system.
     */
    public abstract boolean isExit();
}
