package duke.command;

import duke.core.Storage;
import duke.core.TaskList;

/**
 * Encapsulates a command to list all the tasks present in the task list.
 */
public class ListCommand extends Command {
    /**
     * Displays the tasks present in the task list and returns the output to be displayed by Duke.
     *
     * @param taskList The TaskList object storing all the tasks.
     * @param storage The Storage object which was instantiated with the appropriate storage filepath.
     * @return The output to be displayed by Duke.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) {
        return taskList.toString();
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
