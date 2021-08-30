package command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import task.Task;

/**
 * Command to delete a Task.
 *
 * @author felix-ong
 */
public class DeleteCommand extends Command {
    private String index;
    private Task task;

    public DeleteCommand(String index) {
        this.index = index;
    }

    /**
     * Executes the specific actions for this command.
     *
     * @param tasks   Handles the list of tasks.
     * @param storage Handles the saving and loading of tasks.
     */
    @Override
    public void execute(TaskList tasks, Storage storage) throws DukeException {
        this.task = tasks.deleteTask(storage, this.index);
    }

    /**
     * Returns false.
     *
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String toString() {
        return String.format("Noted! I have removed the following task:%n %s%n", task);
    }
}
