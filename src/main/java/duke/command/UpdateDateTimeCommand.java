package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

public class UpdateDateTimeCommand extends UpdateCommand {

    private int taskNumber;
    private String newDate;
    private String newTime;

    public UpdateDateTimeCommand(int taskNumber, String newDate, String newTime) {
        this.taskNumber = taskNumber;
        this.newDate = newDate;
        this.newTime = newTime;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) {
        Task taskToUpdate = tasks.getTask(taskNumber - 1);
        taskToUpdate.setDateAndTime(newDate, newTime);
        storage.save(tasks);
        return Ui.getUpdatedMessage(taskToUpdate, taskNumber);
    }
}
