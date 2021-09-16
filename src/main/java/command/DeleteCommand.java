package command;

import exception.InvalidValue;
import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

/**
 * DeleteCommand will delete the task stored in local storage when executed.
 */
public class DeleteCommand extends Command {

    private int taskNumber;

    /**
     * Creates a delete-task request using the task number i
     *
     * @param i the task number to be deleted
     */
    public DeleteCommand(int i) {
        this.taskNumber = i;
    }

    public String execute(TaskList tasks, Ui ui, Storage storage) throws InvalidValue {
        try {
            Task deletedTask = tasks.deleteTask(taskNumber);
            storage.write(tasks.getTaskList());
            return String.format("Noted. I've removed this task:\n"
                    + "%s\n"
                    + "Now you have %d tasks in the list.\n", deletedTask, tasks.size());
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidValue();
        }
    }
}
