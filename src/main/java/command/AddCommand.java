package command;

import data.Storage;
import data.TaskList;
import data.Ui;
import task.Task;

/**
 * Command that adds a new Task to Tasklist when executed.
 */
public class AddCommand extends Command {
    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Adds a task to the Tasklist.
     *
     * @param tasks The list of tasks that a user has
     * @param ui The ui that sends a message to the user once the task is added
     * @param storage Saves the updated TaskList to disk
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(task);
        storage.save(tasks);
        ui.showMessage("Task added successfully!");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
