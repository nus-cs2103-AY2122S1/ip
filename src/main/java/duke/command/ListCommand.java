package duke.command;

import duke.DukeException;
import duke.TaskList;

/**
 * The command to list all tasks.
 */
public class ListCommand extends Command {

    private TaskList taskList;

    public ListCommand(TaskList taskList) {
        this.taskList = taskList;
    }

    @Override
    public String execute() throws DukeException {
        return taskList.getAllTask();
    }
}
