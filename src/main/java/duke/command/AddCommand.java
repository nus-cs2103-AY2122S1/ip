package duke.command;

import duke.data.Storage;
import duke.data.TaskList;
import duke.data.Ui;
import duke.task.Task;

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
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(task);
        storage.save(tasks);
        return "Task added successfully!";
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
