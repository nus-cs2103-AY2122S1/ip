package tokio.commands;

import java.io.IOException;

import tokio.exceptions.DukeException;
import tokio.storage.Storage;
import tokio.tasks.Task;
import tokio.tasks.TaskList;
import tokio.ui.Ui;

/**
 * Deletes command specified by user, based on index
 */
public class DeleteCommand extends Command {
    private int index;

    /**
     * Deletes command based on user input.
     *
     * @param index Index for task in the task list.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the delete command.
     *
     * @param tasks Current tasklist.
     * @param ui User input format.
     * @param storage Stores created command into the txt file.
     * @return String message for Tokio's reply.
     * @throws IOException If task to be deleted is null.
     * @throws DukeException If index cannot be found.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException, DukeException {
        assert index >= 0 : "index cannot be < 0";
        if (index < 1) {
            throw new DukeException("Oh no Rio, this index does not exist!\n"
                    + "Please make sure that index > 0");
        }
        int maxIndex = tasks.getSize();
        int deleteIndex = index - 1;
        if (deleteIndex >= maxIndex) {
            throw new DukeException("Oh no Rio, this index does not exist!\n"
                    + "Please make sure that index < size of tasks");
        }
        Task deleteTask = tasks.getTask(deleteIndex);
        storage.editTask(deleteTask, "");
        tasks.deleteTask(deleteIndex);
        return ui.printDeleteCommand(deleteTask, tasks);
    }

    /**
     * Indicates that delete command is not a terminating command.
     *
     * @return False.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
