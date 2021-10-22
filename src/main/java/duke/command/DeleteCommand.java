package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Represents the command of deleting a task.
 */
public class DeleteCommand extends Command {

    private final int taskNo;

    public DeleteCommand(int taskNo) {
        this.taskNo = taskNo;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        int numOfTasks = taskList.getAllTasks().size();
        String msg = taskList.deleteTask(ui, this.taskNo);
        assert numOfTasks - 1 == taskList.getAllTasks().size();
        storage.updateTasks(taskList);
        return msg;
    }
}
