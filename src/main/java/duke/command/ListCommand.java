package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * This ListCommand class represents a command to list the current tasks in the task list.
 */
public class ListCommand extends Command {

    /**
     * Displays the current tasks in the task list to the user.
     *
     * @param tasks The task list.
     * @param ui The UI of the application.
     * @param storage The storage system of the application.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.listTasks(tasks);
    }

    /**
     * Indicates that this command does not intend to exit the system.
     *
     * @return False.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
