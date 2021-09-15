package duke.command;

import duke.DukeException;
import duke.TaskList;

/**
 * The command to mark a task as done.
 */
public class DoneCommand extends Command {

    private TaskList taskList;
    private int index;

    /**
     * Constructor for DoneCommand.
     * @param index The index of the task to be marked as done.
     * @param taskList The list of tasks.
     */
    public DoneCommand(int index, TaskList taskList) {
        this.taskList = taskList;
        this.index = index;
    }

    /**
     * Returns an output message after executing the done command.
     * @return The output message of the command.
     * @throws DukeException Thrown when a duke exception happens.
     */
    @Override
    public String execute() throws DukeException {
        return taskList.markDone(index);
    }
}
