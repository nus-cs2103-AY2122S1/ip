package duke.command;

import duke.core.Storage;
import duke.core.TaskList;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList taskList, Storage storage) {
        taskList.listTasks();
    }

    @Override
    public boolean shouldExit() {
        return false;
    }
}
