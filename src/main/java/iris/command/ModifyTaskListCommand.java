package iris.command;

import iris.Storage;
import iris.TaskList;

public abstract class ModifyTaskListCommand extends Command {
    @Override
    public void store(TaskList tasks, Storage storage) {
        storage.writeTasks(tasks);
    }
}
