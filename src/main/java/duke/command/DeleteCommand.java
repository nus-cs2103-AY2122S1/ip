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

    /**
     * Constructor for DeleteCommand.
     *
     * @param tasks TaskList containing current tasks.
     * @param index The index pointing to the Task in TaskList to operate on, starting from 1.
     */
    public DeleteCommand(TaskList tasks, int index) {
        this.tasks = tasks;
        this.index = index;
    }

    @Override
    public void execute() {
        Ui.displayMessage(this.tasks.deleteTask(this.index));
    }
}
