package duke.task.command;

import duke.DukeException;
import duke.TaskList;

/**
 * The command to delete a specific task;
 */
public class DeleteCommand extends Command {

    private TaskList taskList;
    private int index;

    /**
     * Instantiates a new class object.
     *
     * @param taskList the list of tasks.
     * @param index the index at which item is to be deleted.
     */
    public DeleteCommand(TaskList taskList, int index) {
        this.taskList = taskList;
        this.index = index;
    }

    /**
     * Executes the delete command.
     *
     * @return The output message of the command.
     * @throws DukeException Thrown when a duke exception happens.
     */
    @Override
    public String execute() throws DukeException {
        return taskList.deleteItem(index);
    }
}
