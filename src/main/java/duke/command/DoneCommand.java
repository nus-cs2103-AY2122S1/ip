package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;
/**
 * Represents the command to mark task as done.
 */
public class DoneCommand extends Command {

    private final int taskNo;

    public DoneCommand(int taskNo) {
        this.taskNo = taskNo;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        int numOfTasks = taskList.getAllTasks().size();
        String msg = taskList.markTaskAsDone(ui, this.taskNo);
        assert numOfTasks == taskList.getAllTasks().size();
        storage.updateTasks(taskList);
        return msg;
    }
}
