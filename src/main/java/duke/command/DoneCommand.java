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

    /**
     * Constructor for DoneCommand.
     *
     * @param tasks TaskList containing current tasks.
     * @param index The index pointing to the Task in TaskList to operate on, starting from 1.
     */
    public DoneCommand(TaskList tasks, int index) {
        this.tasks = tasks;
        this.index = index;
    }

    @Override
    public void execute() {
        Ui.displayMessage(this.tasks.markTaskAsDone(this.index));
    }
}
