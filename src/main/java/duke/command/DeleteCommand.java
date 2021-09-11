package duke.command;

import duke.DukeException;
import duke.TaskList;

/**
 * The command to delete a task.
 */
public class DeleteCommand extends Command {

    private TaskList taskList;
    private int index;

    public DeleteCommand(int index, TaskList taskList) {
        this.taskList = taskList;
        this.index = index;
    }

    @Override
    public String execute() throws DukeException {
        return taskList.removeItem(index);
    }
}
