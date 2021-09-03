package duke.command;

import duke.core.Storage;
import duke.core.TaskList;

/**
 * An abstract class encapsulating a command.
 */
public abstract class Command {
    /**
     * Performs the appropriate action to take depending on run-time type of the Command object and returns the output
     * to be displayed by Duke.
     *
     * @param taskList The TaskList object storing all the tasks.
     * @param storage The Storage object which was instantiated with the appropriate storage filepath.
     * @return The output to be displayed by Duke.
     */
    public abstract String execute(TaskList taskList, Storage storage);

    /**
     * Returns a boolean specifying whether Duke should terminate.
     *
     * @return A boolean specifying whether Duke should terminate.
     */
    public abstract boolean shouldExit();
}
