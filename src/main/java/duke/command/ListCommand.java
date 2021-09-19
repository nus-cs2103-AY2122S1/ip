package duke.command;

import duke.data.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * ListCommand represents a command to show a user all tasks in the task list.
 */
public class ListCommand extends Command {
    /**
     * Executes the ListCommand object.
     *
     * @param taskList the current task list
     * @param ui the ui object used
     * @param storage the current storage
     * @return a string message representation of all tasks in the task list
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return ui.showAllTasks(taskList);
    }
}
