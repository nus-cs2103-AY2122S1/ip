package duke.command;

import duke.task.Task;
import duke.task.TaskList;
import duke.util.DukeException;
import duke.util.Storage;

/** Edit task name of the task. */
public class UpdateNameCommand extends Command {
    private int taskNumber;
    private String newName;

    /**
     * Constructs an command to update task name.
     * @param taskNumber task to be updated.
     * @param newName name for task to be updated to.
     */
    public UpdateNameCommand(int taskNumber, String newName) {
        this.taskNumber = taskNumber;
        this.newName = newName;
    }

    /**
     * Changes the name of a task in the task list.
     *
     * @param storage storage instance initialised when duke is created.
     * @param taskList task list instance initialised when duke is created.
     * @return String to indicated if command executed successfully.
     * @throws DukeException if task number is invalid.
     */
    @Override
    public String execute(Storage storage, TaskList taskList) throws DukeException {
        try {
            Task task = taskList.updateTaskName(this.taskNumber, this.newName);
            storage.writeToDisk(taskList.compileTasks());
            return String.format("Can do! Task updated\n  %s", task);
        } catch (IndexOutOfBoundsException | NullPointerException e) {
            throw new DukeException(String.format("Task number %d invalid.", taskNumber));
        }
    }
}
