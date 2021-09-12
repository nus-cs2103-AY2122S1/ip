package duke.command;

import duke.task.Task;
import duke.task.TaskList;
import duke.util.DukeException;
import duke.util.Storage;

/**
 * Mark a task in the task list as done.
 */
public class DoneCommand extends Command {
    private final int taskNumber;

    /**
     * Constructs a command that marks a task as complete.
     *
     * @param taskNumber task to be marked as complete.
     */
    public DoneCommand(int taskNumber) {
        assert taskNumber > 0 : "Task number should be greater than 0";
        this.taskNumber = taskNumber;
    }

    /**
     * Marks tasks associated to task number. Task number is equivalent to the index
     * of the task in task list + 1.
     *
     * @param storage storage instance initialised when duke is created.
     * @param taskList task list instance initialised when duke is created.
     * @return String to indicate whether task is mark completed,or wrong format entered.
     * @throws DukeException task number is invalid.
     */
    @Override
    public String execute(Storage storage, TaskList taskList) throws DukeException {
        try {
            Task task = taskList.markAsDone(this.taskNumber);
            storage.writeToDisk(taskList.compileTasks());
            return String.format("Can do! Task %d marked as done:\n  %s",
                    taskNumber,
                    task);
        } catch (IndexOutOfBoundsException | NullPointerException e) {
            throw new DukeException(String.format("Task number %d invalid.", taskNumber));
        }
    }
}
