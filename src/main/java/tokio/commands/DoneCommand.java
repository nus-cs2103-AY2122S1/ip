package tokio.commands;

import java.io.IOException;

import tokio.exceptions.DukeException;
import tokio.storage.Storage;
import tokio.tasks.Task;
import tokio.tasks.TaskList;
import tokio.ui.Ui;

/**
 * Marks task specified by user as done, using index.
 */
public class DoneCommand extends Command {
    protected int index;

    /**
     * Constructor for DoneCommand.
     *
     * @param index Index for task in the task list.
     */
    public DoneCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the done command.
     *
     * @param tasks Existing tasks in the task list.
     * @param ui User input format.
     * @param storage Stores created command into the txt file.
     * @return String message for Tokio's reply.
     * @throws IOException If task cannot be written to the storage file.
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
        int doneIndex = index - 1;
        if (doneIndex >= maxIndex) {
            throw new DukeException("Oh no Rio, this index does not exist!\n"
                    + "Please make sure that index < size of tasks");
        }
        Task doneTask = tasks.getTask(doneIndex);
        storage.editTask(doneTask, doneTask.formatToStorage());
        tasks.doneTask(doneIndex);
        return ui.printDoneCommand(doneTask, tasks);
    }

    /**
     * Indicates that done command is not a terminating command.
     *
     * @return False.
     */
    @Override
    public boolean isExit() {
        return false;
    }

}
