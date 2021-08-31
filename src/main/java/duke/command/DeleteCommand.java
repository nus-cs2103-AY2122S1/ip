package duke.command;

import duke.storage.Storage;
import duke.storage.TaskList;
import duke.task.Task;
import duke.ui.Ui;

public class DeleteCommand extends Command {
    private final int serialNo;

    public DeleteCommand(int serialNo) {
        this.serialNo = serialNo;
    }

    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) {
        Task task = taskList.remove(serialNo);
        storage.write(taskList.getTasks());
        ui.displayRemovedTask(task, taskList.getTaskCount());
    }
}
