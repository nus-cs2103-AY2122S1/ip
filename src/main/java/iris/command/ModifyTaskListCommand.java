package iris.command;

import iris.IrisException;
import iris.Storage;
import iris.TaskList;
import iris.Ui;

public abstract class ModifyTaskListCommand extends Command {
    @Override
    public void run(TaskList tasks, Ui ui, Storage storage) throws IrisException {
        super.run(tasks, ui, storage);
        storage.writeTasks(tasks);
    }
}
