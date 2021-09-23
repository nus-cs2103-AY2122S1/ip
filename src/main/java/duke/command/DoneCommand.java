package duke.command;

import duke.core.Storage;
import duke.core.TaskList;

import java.io.IOException;

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
        assert indexOfCompleted > 0: "Index of completed task should be at least 1";
        this.indexOfCompleted = indexOfCompleted;
    }

    /**
     * Marks the task as done in the task list and storage file and returns the output to be displayed by Duke.
     *
     * @param taskList The TaskList object storing all the tasks.
     * @param storage The Storage object which was instantiated with the appropriate storage filepath.
     * @return The output to be displayed by Duke.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) {
        String output = taskList.markAsDone(indexOfCompleted);
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
