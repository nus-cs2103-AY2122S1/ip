package duke.command;

import duke.task.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;
import duke.task.Task;

public class DeleteCommand extends Command {

    private final int taskNumber;

    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Task removed = taskList.delete(taskNumber);
        ui.deletedMsg();
        ui.showTask(removed);
        ui.showListLength(taskList);
        storage.save(taskList);
    }

}
