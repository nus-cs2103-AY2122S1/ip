package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

public class UpdateDateTimeCommand extends UpdateCommand {

    /** The task number of the task to update */
    private int taskNumber;
    /** The new date of the task */
    private String newDate;
    /** The new time of the task */
    private String newTime;

    /**
     * Constructor for a new command to update the date and time of a task.
     *
     * @param taskNumber The task number to update.
     * @param newDate The new date of the task.
     * @param newTime The new time of the task.
     */
    public UpdateDateTimeCommand(int taskNumber, String newDate, String newTime) {
        this.taskNumber = taskNumber;
        this.newDate = newDate;
        this.newTime = newTime;
    }

    /**
     * Executes the command to update the date and time of the task.
     *
     * @param tasks The user's task list.
     * @param storage The storage object used to save data to the data file.
     * @return A message informing the user that the task has been updated
     * successfully.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        Task taskToUpdate = tasks.getTask(taskNumber - 1);
        taskToUpdate.setDateAndTime(newDate, newTime);
        storage.save(tasks);
        return Ui.getUpdatedMessage(taskToUpdate, taskNumber);
    }
}
