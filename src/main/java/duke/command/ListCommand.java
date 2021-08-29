package duke.command;

import duke.ResponseLogic;
import duke.Storage;
import duke.task.TaskList;

public class ListCommand extends Command {

    @Override
    public String execute(TaskList tasks, ResponseLogic responseLogic, Storage storage) {
        return tasks.getTaskList(responseLogic);
    }
}
