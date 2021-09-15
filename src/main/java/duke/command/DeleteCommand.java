package duke.command;

import duke.DukeException;
import duke.TaskList;

/**
 * The command to delete a task.
 */
public class DeleteCommand extends Command {

    private TaskList taskList;
    private int index;

    /**
     * Constructor for DeleteCommand.
     * @param index The index of the item to be deleted, 0-based.
     * @param taskList The list of tasks.
     */
    public DeleteCommand(int index, TaskList taskList) {
        this.taskList = taskList;
        this.index = index;
    }

    /**
     * Returns an output message after executing the delete command.
     * @return The output message of the command.
     * @throws DukeException Thrown when a duke exception happens.
     */
    @Override
    public String execute() throws DukeException {
        return taskList.removeItem(index);
    }
}
