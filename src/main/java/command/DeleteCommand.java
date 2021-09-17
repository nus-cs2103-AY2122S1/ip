package command;

import exception.InvalidValue;
import storage.Storage;
import task.Task;
import task.TaskList;

/**
 * DeleteCommand will delete the task stored in local storage when executed.
 */
public class DeleteCommand extends Command {

    private int taskNumber;

    /**
     * Creates a delete-task request using the task number i.
     *
     * @param i the task number to be deleted
     */
    public DeleteCommand(int i) {
        this.taskNumber = i;
    }

    /**
     * Executes the given command returned by parse method.
     * Each command class will have its own interaction with Ui, TaskList and Storage
     *
     * @param tasks the TaskList loaded from storage.
     * @param storage accesses the file location in local storage.
     * @throws InvalidValue If there is no task at the given index.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws InvalidValue {
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
