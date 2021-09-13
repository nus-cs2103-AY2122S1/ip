package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

public class UpdateFullCommand extends UpdateCommand {

    private int taskNumber;
    private String newCommandDesc;
    private String newDate;
    private String newTime;


    public UpdateFullCommand(int taskNumber, String newCommandDes, String newDate, String newTime) {
        this.taskNumber = taskNumber;
        this.newCommandDesc = newCommandDes;
        this.newDate = newDate;
        this.newTime = newTime;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) {
        Task taskToUpdate = tasks.getTask(taskNumber - 1);
        taskToUpdate.setTaskDetails(newCommandDesc);
        taskToUpdate.setDateAndTime(newDate, newTime);
        storage.save(tasks);
        return Ui.getUpdatedMessage(taskToUpdate, taskNumber);
    }
}
