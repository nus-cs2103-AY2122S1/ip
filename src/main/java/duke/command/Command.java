package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.exception.DukeException;

/**
 * Represents a general Command to be executed.
 */
public abstract class Command {

    /**
     * Executes the necessary command using the given TaskList, Ui and Storage.
     *
     * @param tasks The given TaskList to be updated.
     * @param storage The given Storage to save changes to.
     * @return The response to the user.
     * @throws DukeException When a command is unable to be executed.
     */
    public abstract String execute(TaskList tasks, Storage storage) throws DukeException;
}
