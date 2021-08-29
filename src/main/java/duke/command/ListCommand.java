package duke.command;

import duke.task.TaskList;

import duke.util.Ui;
import duke.util.Storage;

/**
 * A class that represents the command when the user types in 'list'.
 */
public class ListCommand extends Command {

    /**
     * Displays all tasks in the user's task list.
     *
     * @param tasks   The current list of tasks from the user.
     * @param ui      An object that handles all UI related functionality. (e.g. printing)
     * @param storage An object that handles all save/load related functionality.
     * @return The input task list, unmodified.
     */
    @Override
    public TaskList execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMessage("Here is your task list:");
        ui.showIndentedMessage(tasks.toString());
        return tasks;
    }
}
