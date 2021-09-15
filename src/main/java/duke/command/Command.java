package duke.command;

import duke.exception.DukeException;
import duke.response.DukeResponse;
import duke.storage.Storage;
import duke.task.TaskManager;

/**
 * Represents a command entered by the user.
 */
public abstract class Command {
    /**
     * Executes the command.
     *
     * @param taskManager <code>TaskManager</code> responsible for keeping track of the existing tasks.
     * @param storage <code>Storage</code> used to save updated task data.
     * @throws DukeException If any errors occur during execution.
     */
    public abstract DukeResponse execute(TaskManager taskManager, Storage storage) throws DukeException;
}

