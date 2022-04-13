package duke.commands;

import duke.DukeException;
import duke.TaskList;

/**
 * Represents an executable command.
 */
public abstract class Command {

    /**
     * Executes the command and returns the result.
     * @param tasks TaskList to execute the command on.
     * @throws DukeException if input is invalid.
     */
    public abstract String execute(TaskList tasks) throws DukeException;
}
