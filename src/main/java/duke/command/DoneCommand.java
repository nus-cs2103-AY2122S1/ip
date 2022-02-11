package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents the command when the user wants to mark a task as done.
 */
public class DoneCommand extends Command {

    public static final String COMMAND_WORD = "done";
    public static final String INVALID_TASK_NUMBER_MESSAGE =
            "This is not a valid task number.";

    /** The task number of the task that the user wants to mark as done */
    private int taskNumber;

    /**
     * Constructs a DoneCommand object
     *
     * @param taskNumber The task number that the user wants to mark as done.
     */
    public DoneCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Executes the command to mark a task in the task list as done.
     *
     * @param tasks The user's task list.
     * @param storage Storage object used to save the task list to the data file.
     * @return A message informing the user that the task specified has been successfully marked
     * as done.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        try {
            Task completedTask = tasks.markDone(taskNumber);
            storage.save(tasks);
            return Ui.getDoneMessage(completedTask);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(INVALID_TASK_NUMBER_MESSAGE);
        }
    }
}
