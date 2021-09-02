package duke.commands;

import duke.data.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Represents an executable command.
 */
public abstract class Command {

    /**
     * Executes the command.
     *
     * @param tasks List of tasks
     * @param ui User interface class
     * @param storage Storage class
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    /**
     * Returns if the command was to exit the application.
     */
    public boolean isExit() {
        return false;
    }
}
