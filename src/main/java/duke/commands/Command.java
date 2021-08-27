package duke.commands;

import duke.data.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;

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
    abstract public void execute(TaskList tasks, Ui ui, Storage storage);

    /**
     * Returns if the command was to exit the application.
     */
    public boolean isExit() {
        return false;
    }
}
