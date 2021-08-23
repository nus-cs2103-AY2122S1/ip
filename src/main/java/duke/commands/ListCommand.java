package duke.commands;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * This class encapsulates the list command.
 */
public class ListCommand extends Command {
    /**
     * Prints the list of tasks.
     *
     * @param tasks The current list of tasks.
     * @param ui The UI of the Duke app.
     * @param storage The storage manager for the Duke app.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printAllTasks(tasks);
    }
}
