package ponyo.commands;

import ponyo.data.task.TaskList;
import ponyo.ui.Ui;
import ponyo.storage.Storage;

/**
 * Represents an executable command
 */
public abstract class Command {
    /**
     * Executes the command and returns the result.
     *
     * @param tasks The list of tasks stored.
     * @param ui A Ui object to communicate with the user.
     * @param storage Load tasks and save tasks to file
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    /**
     * @return true if the command is meant to exit the program,
     *         false otherwise.
     */
    public abstract boolean isExit();
}
