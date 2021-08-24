package command;

import data.Storage;
import data.TaskList;
import data.Ui;

/**
 * Command that displays a user's saved tasks when executed.
 */
public class ListCommand extends Command {
    /**
     * Displays a user's saved tasks.
     *
     * @param tasks The list of tasks that a user has
     * @param ui The ui that sends TaskList to the user
     * @param storage Not used for this command
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.getSize() == 0) {
            ui.showMessage("You have no tasks saved!");
        } else {
            ui.showTasks(tasks);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
