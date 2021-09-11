package tokio.commands;

import java.io.IOException;

import tokio.storage.Storage;
import tokio.tasks.Task;
import tokio.tasks.TaskList;
import tokio.ui.Ui;

/**
 * Deletes command specified by user based on index
 */
public class DeleteCommand extends Command {
    private int index;

    /**
     * Deletes command based on user input.
     *
     * @param cmdLine User input.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the delete command.
     *
     * @param tasks current tasklist.
     * @param ui user input format.
     * @param storage stores created command into the txt file.
     * @throws IOException if task to be deleted is null.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        assert index >= 0 : "index cannot be < 0";
        if (index < 1) {
            return ui.printInvalidIndexError();
        }
        int maxIndex = tasks.getSize();
        int deleteIndex = index - 1;
        if (deleteIndex >= maxIndex) {
            return ui.printInvalidIndexError();
        }
        Task deleteTask = tasks.getTask(deleteIndex);
        tasks.deleteTask(deleteIndex);
        storage.deleteTask(deleteTask);
        return ui.printDeleteCommand(deleteTask, tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
