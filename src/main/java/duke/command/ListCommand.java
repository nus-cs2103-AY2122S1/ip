package duke.command;

import duke.ResponseLogic;
import duke.Storage;
import duke.task.TaskList;

/** The Command class responsible for retrieving the list of tasks. */
public class ListCommand extends Command {

    @Override
    public String execute(TaskList tasks, ResponseLogic responseLogic, Storage storage) {
        return tasks.getTaskList(responseLogic);
    }
}
