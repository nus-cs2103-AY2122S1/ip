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
    private final boolean EXIT = false;

    /**
     * Creates a delete task request using the task number i
     *
     * @param i the task number to be deleted
     */
    public DeleteCommand(int i) {
        this.taskNumber = i;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage)  throws InvalidValue {
        try {
            Task deletedTask = tasks.deleteTask(taskNumber);
            storage.write(tasks.getTaskList());
            System.out.printf("\tNoted. I've removed this task:\n" +
                    "\t%s\n" +
                    "\tNow you have %d tasks in the list.\n", deletedTask, tasks.size());
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidValue();
        }
    }

    public boolean isExit() {
        return EXIT;
    }
}
