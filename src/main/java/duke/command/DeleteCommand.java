package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Type of Command that deletes a task from the task list.
 */
public class DeleteCommand extends Command {
    private int taskToDelete;

    /**
     * Constructor.
     *
     * @param taskToDelete current position of task to delete in the task list.
     */
    public DeleteCommand(int taskToDelete) {
        this.taskToDelete = taskToDelete;
    }

    /**
     * Executes a series of operations to delete the task.
     *
     * @param taskList
     * @param ui
     * @param storage
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        storage.delete(this.taskToDelete);
        String message = ui.delete(taskList, this.taskToDelete);
        taskList.deleteTask(this.taskToDelete);
        return message;
    }

    /**
     * Not an Exit Command
     *
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
