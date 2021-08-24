package command;

import data.DukeException;
import data.Storage;
import data.TaskList;
import data.Ui;

/**
 * Command that deletes a Task from Tasklist when executed.
 */
public class DeleteCommand extends Command {
    /**
     * Index of the task in TaskList
     */
    private int taskNumber;

    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Deletes a task from the Tasklist.
     *
     * @param tasks The list of tasks that a user has
     * @param ui The ui that sends a message to the user once the task is deleted
     * @param storage Saves the updated TaskList to disk
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (taskNumber > tasks.getSize() || taskNumber <= 0) {
            throw new DukeException("Please insert a valid task.Task Number!");
        } else {
            tasks.deleteTask(taskNumber);
            storage.save(tasks);
            ui.showMessage("Task deleted!");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
