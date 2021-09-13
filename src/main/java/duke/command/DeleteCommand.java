package duke.command;

import duke.task.Task;
import duke.task.TaskList;
import duke.util.DukeException;
import duke.util.Storage;

/**
 *
 * Remove a task from the task list.
 */
public class DeleteCommand extends Command {
    private final int taskNumber;

    /**
     * Constructs a command that deletes a task from the task list.
     *
     * @param taskNumber task number to be deleted
     */
    public DeleteCommand(int taskNumber) {
        assert taskNumber > 0 : "Task number should be greater than 0";
        this.taskNumber = taskNumber;
    }

    /**
     * Deletes task associated to task number. Task number is equivalent to the index
     * of the task in task list + 1.
     *
     * @param storage storage instance initialised when duke is created.
     * @param taskList task list instance initialised when duke is created.
     * @return String on whether task is deleted, or wrong format entered.
     * @throws DukeException task number is invalid.
     */
    @Override
    public String execute(Storage storage, TaskList taskList) throws DukeException {
        try {
            Task task = taskList.deleteTask(taskNumber);
            storage.writeToDisk(taskList.compileTasks());
            return String
                    .format("Can do! Task %d deleted:\n  %s\nNow you have %d tasks in the list.",
                            taskNumber,
                            task,
                            taskList.getSize());
        } catch (IndexOutOfBoundsException | NullPointerException e) {
            throw new DukeException(String.format("Task number %d invalid.", taskNumber));
        }
    }
}
