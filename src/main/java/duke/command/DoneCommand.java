package duke.command;

import duke.util.TaskList;
import duke.util.Ui;

/**
 * Class that represents the Command to mark a Task as completed from the TaskList.
 *
 * @author Benedict Chua
 */
public class DoneCommand extends Command {
    private TaskList tasks;
    private int index;

    public DoneCommand(TaskList taskList, int index) {
        this.tasks = taskList;
        this.index = index;
    }

    @Override
    public void execute() {
        Ui.displayMessage(this.tasks.markTaskAsDone(this.index));
    }
}