package iris.command;

import iris.IrisException;
import iris.Storage;
import iris.TaskList;

public abstract class ModifyTaskListCommand extends Command {
    @Override
    public void store(TaskList tasks, Storage storage) throws IrisException {
        storage.writeTasks(tasks);
    }
}
