package duke.command;

import duke.core.Storage;
import duke.core.TaskList;

/**
 * An abstract class encapsulating a command.
 */
public abstract class Command {
    /**
     * The appropriate action to take depending on run-time type of the Command object.
     *
     * @param taskList The TaskList object storing all the tasks.
     * @param storage The Storage object which was instantiated with the appropriate storage filepath.
     */
    public abstract void execute(TaskList taskList, Storage storage);

    /**
     * Returns a boolean specifying whether Duke should terminate.
     *
     * @return A boolean specifying whether Duke should terminate.
     */
    public abstract boolean shouldExit();
}
