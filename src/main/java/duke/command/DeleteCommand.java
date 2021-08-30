package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class DeleteCommand extends Command {

    private final int taskNo;

    public DeleteCommand(int taskNo) {
        this.taskNo = taskNo;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        String msg = taskList.deleteTask(ui, this.taskNo);
        storage.updateTasks(taskList);
        return msg;
    }
}
