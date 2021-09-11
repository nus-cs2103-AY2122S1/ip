package duke.command;

import duke.DukeException;

/**
 * An abstract class that encapsulates a command.
 */
public abstract class Command {

    /**
     * Returns an output message after executing a command.
     * @return The output message of the command.
     * @throws DukeException Thrown when a duke exception happens.
     */
    public abstract String execute() throws DukeException;
}
