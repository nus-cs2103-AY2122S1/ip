package duke.task.command;

import java.io.IOException;

import duke.DukeException;

/**
 * An abstract class to execute commands entered by user.
 */
public abstract class Command {

    /**
     * Executes the command.
     *
     * @return The output message of the command.
     * @throws DukeException Thrown when a duke exception happens.
     */
    public abstract String execute() throws DukeException, IOException;
}
