package duke.command;

import duke.core.Storage;
import duke.core.TaskList;

/**
 * Encapsulates a command to delete a task based on its index.
 */
public class DeleteCommand extends Command {
    private int indexToDelete;

    /**
     * Constructs a DeleteCommand object.
     *
     * @param indexToDelete Index of the task to be deleted.
     */
    public DeleteCommand(int indexToDelete) {
        this.indexToDelete = indexToDelete;
    }

    /**
     * Delete the task from task list and storage file.
     *
     * @param taskList The TaskList object storing all the tasks.
     * @param storage The Storage object which was instantiated with the appropriate storage filepath.
     */
    @Override
    public void execute(TaskList taskList, Storage storage) {
        taskList.delete(indexToDelete);
        storage.saveTasksToFile(taskList);
    }

    /**
     * Returns a boolean specifying whether Duke should terminate.
     *
     * @return false
     */
    @Override
    public boolean shouldExit() {
        return false;
    }
}

