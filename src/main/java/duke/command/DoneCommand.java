package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class DoneCommand extends Command {

    private final int taskNo;

    public DoneCommand(int taskNo) {
        this.taskNo = taskNo;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        String msg = taskList.markTaskAsDone(ui, this.taskNo);
        storage.updateTasks(taskList);
        return msg;
    }
}
