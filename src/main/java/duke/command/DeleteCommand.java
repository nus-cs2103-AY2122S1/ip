package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents the command when the user wants to delete a task from the task list.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";
    public static final String INVALID_TASK_NUMBER_MESSAGE =
            "This is not a valid task number.";

    /** The task number of the task that the user wants to delete */
    private int taskNumber;

    /**
     * Constructs a DeleteCommand object.
     *
     * @param taskNumber The task number of the task that the user wants to delete.
     */
    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Executes the command to mark a task in the task list as done.
     *
     * @param tasks The user's task list.
     * @param storage Storage object used to save the task list to the data file.
     * @return A message informing the user that the task specified has been successfully
     * deleted and also shows the number of tasks left in the task list.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        try {
            Task removedTask = tasks.deleteTask(taskNumber);
            storage.save(tasks);
            return Ui.getDeleteMessage(removedTask, tasks.size());
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(INVALID_TASK_NUMBER_MESSAGE);
        }
    }
}
