package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Represents the command of adding a task.
 */
public class AddCommand extends Command {

    private final Task task;

    /**
     * Creates the command that will add the task provided to the taskList.
     * @param task The task that will be added.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes the command by creating the task and printing the message through the ui.
     * @param taskList The taskList where the task will be added.
     * @param ui The ui where the message will be printed.
     * @param storage The storage where it will be updated with the new task.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        String msg = taskList.addTask(ui, this.task);
        storage.updateTasks(taskList);
        return msg;
    }
}
