package duke.command;

import java.time.LocalDateTime;

import duke.task.Task;
import duke.task.TaskList;
import duke.util.DukeException;
import duke.util.Storage;

/**
 * Updates the date time attribute of a task.
 */
public class UpdateDateTimeCommand extends Command {
    private int taskNumber;
    private LocalDateTime dateTime;

    /**
     * Constructs a command to update date time of a task.
     *
     * @param taskNumber task number to be updated.
     * @param dateTime new date time value for the task.
     */
    public UpdateDateTimeCommand(int taskNumber, LocalDateTime dateTime) {
        this.taskNumber = taskNumber;
        this.dateTime = dateTime;
    }

    /**
     * Changes the dateTime of a task in the task list.
     *
     * @param storage storage instance initialised when duke is created.
     * @param taskList task list instance initialised when duke is created.
     * @return String to indicate if command executed successfully.
     * @throws DukeException if datetime attribute does not exist.
     */
    @Override
    public String execute(Storage storage, TaskList taskList) throws DukeException {
        try {
            Task task = taskList.updateTaskDateTime(this.taskNumber, this.dateTime);
            storage.writeToDisk(taskList.compileTasks());
            return String.format("Can do! Task updated\n  %s", task);
        } catch (IndexOutOfBoundsException | NullPointerException e) {
            throw new DukeException(String.format("Task number %d invalid.", taskNumber));
        }
    }
}
