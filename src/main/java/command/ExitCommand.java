package command;

import data.Storage;
import data.TaskList;
import data.Ui;

/**
 * Command that exits Duke when executed.
 */
public class ExitCommand extends Command {

    /**
     * Saves the current tasks to disk and displays exit message.
     *
     * @param tasks The list of tasks that a user has
     * @param ui The ui that sends a message to the user once the tasks are saved
     * @param storage Saves the updated TaskList to disk
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        storage.save(tasks);
        ui.showGoodbye();
    }

    /**
     * Allows the program to be exited.
     *
     * @return True
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
