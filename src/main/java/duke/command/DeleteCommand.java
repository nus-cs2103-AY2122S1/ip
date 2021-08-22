package duke.command;

import duke.util.TaskList;
import duke.util.Ui;

/**
 * Class that represents the Command to delete from the TaskList.
 *
 * @author Benedict Chua
 */
public class DeleteCommand extends Command {
    private TaskList taskList;
    private int index;

    public DeleteCommand(TaskList taskList, int index) {
        this.taskList = taskList;
        this.index = index;
    }

    @Override
    public void execute() {
        Ui.displayMessage(this.taskList.deleteTask(this.index));
    }
}