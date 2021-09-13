package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

public class UpdateDescriptionCommand extends UpdateCommand {
    /** The task number of the task to update */
    private int taskNumber;
    /** The new description of the task */
    private String newDescription;

    /**
     * Constructor for a new command to update the description of a task.
     *
     * @param taskNumber The task number to update.
     * @param newDescription The new description of the task.
     */
    public UpdateDescriptionCommand(int taskNumber, String newDescription) {
        this.taskNumber = taskNumber;
        this.newDescription = newDescription;
    }

    /**
     * Executes the command to update the description of the task.
     *
     * @param tasks The user's task list.
     * @param storage The storage object used to save data to the data file.
     * @return A message informing the user that the task has been updated
     * successfully.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        Task taskToUpdate = tasks.getTask(taskNumber - 1);
        taskToUpdate.setTaskDetails(newDescription);
        storage.save(tasks);
        return Ui.getUpdatedMessage(taskToUpdate, taskNumber);
    }
}
