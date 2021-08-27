package duke.command;

import duke.util.TaskList;
import duke.util.Ui;

/**
 * Class that represents the Command to delete from the TaskList.
 *
 * @author Benedict Chua
 */
public class DeleteCommand extends Command {
    private TaskList tasks;
    private int index;

    public DeleteCommand(TaskList taskList, int index) {
        this.tasks = taskList;
        this.index = index;
    }

    @Override
    public void execute() {
        Ui.displayMessage(this.tasks.deleteTask(this.index));
    }
}