package duke.commands;

import duke.DukeException;
import duke.util.PersistentStorage;
import duke.util.Response;
import duke.util.Tasklist;

/**
 * An abstract class that all Commands inherit from.
 */
public abstract class Command {

    /**
     * Executes the command based on the type of the calling Command.
     *
     * @param taskList The Tasklist associated with the Duke instance.
     * @param response The UI associated with the Duke instance.
     * @param storage The PersistentStorage associated with the Duke instance.
     * @throws DukeException If an error occurs while executing the command.
     * @returns A CommandResult encapsulating the results of executing the command.
     */
    public abstract CommandResult executeCommand(Tasklist taskList, Response response, PersistentStorage storage)
            throws DukeException;

    /**
     * Returns a Boolean that tells Duke if it should terminate or not.
     * Default implementation of a command does not terminate Duke.
     *
     * @return False by default, unless overridden
     */
    public Boolean isExit() {
        return false;
    };
}
