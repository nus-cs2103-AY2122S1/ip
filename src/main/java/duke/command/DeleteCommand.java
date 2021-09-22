package duke.command;

import duke.core.Storage;
import duke.core.TaskList;

import java.io.IOException;

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
        assert indexToDelete > 0: "Index to delete should be at least 1";
        this.indexToDelete = indexToDelete;
    }

    /**
     * Deletes the task from task list and storage file and returns the output to be displayed by Duke.
     *
     * @param taskList The TaskList object storing all the tasks.
     * @param storage The Storage object which was instantiated with the appropriate storage filepath.
     * @return The output to be displayed by Duke.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) {
        String output = taskList.delete(indexToDelete);
        try {
            storage.saveTasksToFile(taskList);
        } catch (IOException e) {
            output = String.format("%s\n%s", output, e.getMessage());
        }
        return output;
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

