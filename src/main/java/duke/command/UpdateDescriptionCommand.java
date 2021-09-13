package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

public class UpdateDescriptionCommand extends UpdateCommand {
    private int taskNumber;
    private String newDescription;

    public UpdateDescriptionCommand(int taskNumber, String newDescription) {
        this.taskNumber = taskNumber;
        this.newDescription = newDescription;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) {
        Task taskToUpdate = tasks.getTask(taskNumber - 1);
        taskToUpdate.setTaskDetails(newDescription);
        storage.save(tasks);
        return Ui.getUpdatedMessage(taskToUpdate, taskNumber);
    }
}
