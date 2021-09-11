package duke.command;

import duke.DukeException;
import duke.TaskList;

/**
 * The command to mark a task as done.
 */
public class DoneCommand extends Command {

    private TaskList taskList;
    private int index;

    public DoneCommand(TaskList taskList, int index) {
        this.taskList = taskList;
        this.index = index;
    }

    @Override
    public String execute() throws DukeException {
        return taskList.markDone(index);
    }
}
