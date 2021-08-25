package command;

import core.Storage;
import core.TaskList;

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
