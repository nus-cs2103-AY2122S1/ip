package duke.command;

import duke.storage.Storage;
import duke.storage.TaskList;
import duke.task.Task;
import duke.ui.Ui;

public class DoneCommand extends Command {
    private final int serialNo;

    public DoneCommand(int serialNo) {
        this.serialNo = serialNo;
    }

    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) {
        Task task = taskList.markDone(serialNo);
        storage.write(taskList.getTasks());
        ui.displayDoneTask(task);
    }
}
