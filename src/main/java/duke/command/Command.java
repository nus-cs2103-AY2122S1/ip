package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;

/**
 * An abstract class representing a command
 * @author KelvinSoo
 * @version A-MoreOOP
 */
public abstract class Command {

    /**
     * Execute a command.
     * @param taskList The task list to execute the command on.
     * @param storage The place to store the session.
     * @throws DukeException execution errors.
     * @return The response
     */
    public abstract String execute(TaskList taskList, Storage storage) throws DukeException;

    public abstract boolean isExit();
}
