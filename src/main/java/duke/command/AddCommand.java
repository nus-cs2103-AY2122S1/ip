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
     *
     * @param task the task that will be added.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes the command by creating the task and printing the message through the ui.
     *
     * @param taskList the taskList where the task will be added.
     * @param ui the ui where the message will be printed.
     * @param storage the storage where it will be updated with the new task.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        int numOfTasks = taskList.getAllTasks().size();
        String msg = taskList.addTask(ui, this.task);
        assert numOfTasks + 1 == taskList.getAllTasks().size();
        storage.updateTasks(taskList);
        return msg;
    }
}
