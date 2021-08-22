package duke.command;

import duke.util.TaskList;
import duke.util.Ui;

/**
 * Class that represents the Command to mark a Task as completed from the TaskList.
 *
 * @author Benedict Chua
 */
public class DoneCommand extends Command {
    private TaskList taskList;
    private int index;

    public DoneCommand(TaskList taskList, int index) {
        this.taskList = taskList;
        this.index = index;
    }

    @Override
    public void execute() {
        Ui.displayMessage(this.taskList.markTaskAsDone(this.index));
    }
}