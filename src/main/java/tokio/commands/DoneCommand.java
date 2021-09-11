package tokio.commands;

import java.io.IOException;

import tokio.storage.Storage;
import tokio.tasks.Task;
import tokio.tasks.TaskList;
import tokio.ui.Ui;

/**
 * Marks task specified by user as done, using index.
 */
public class DoneCommand extends Command {
    protected int index;

    public DoneCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        assert index >= 0 : "index cannot be < 0";
        if (index < 1) {
            return ui.printInvalidIndexError();
        }
        int maxIndex = tasks.getSize();
        int doneIndex = index - 1;
        if (doneIndex >= maxIndex) {
            return ui.printInvalidIndexError();
        }
        Task doneTask = tasks.getTask(doneIndex);
        storage.doneTask(doneTask);
        tasks.doneTask(doneIndex);
        return ui.printDoneCommand(doneTask, tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
