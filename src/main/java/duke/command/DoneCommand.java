package duke.command;

import duke.core.Storage;
import duke.core.TaskList;

/**
 * Encapsulates a command to mark a task as completed based on index.
 */
public class DoneCommand extends Command {
    private int indexOfCompleted;

    /**
     * Constructs a DoneCommand object.
     *
     * @param indexOfCompleted Index of the task that has been completed.
     */
    public DoneCommand(int indexOfCompleted) {
        this.indexOfCompleted = indexOfCompleted;
    }

    /**
     * Mark the task as done in the task list and storage file.
     *
     * @param taskList The TaskList object storing all the tasks.
     * @param storage The Storage object which was instantiated with the appropriate storage filepath.
     */
    @Override
    public void execute(TaskList taskList, Storage storage) {
        taskList.markAsDone(indexOfCompleted);
        storage.addTasksToFile(taskList);
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
