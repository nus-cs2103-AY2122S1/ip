package duke.task.command;

import duke.DukeException;
import duke.TaskList;

/**
 * The command to mark a task as done.
 */
public class DoneCommand extends Command {

    private TaskList taskList;
    private int index;

    /**
     * Instantiates a new class object.
     *
     * @param taskList the list of tasks.
     * @param index index at which task to be marked done is.
     */
    public DoneCommand(TaskList taskList, int index) {
        this.taskList = taskList;
        this.index = index;
    }

    /**
     * Executes the command.
     *
     * @return The output message of the command.
     * @throws DukeException Thrown when a duke exception happens.
     */
    @Override
    public String execute() throws DukeException {
        return taskList.markDone(index);
    }
}
