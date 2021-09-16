package duke.command;

import java.io.IOException;

import duke.exception.DukeException;
import duke.misc.TaskList;

/**
 * Command class to represent an executable command.
 */
public abstract class Command {

    /**
     * Executes the appropriate operations according to command type.
     *
     * @param tl The current TaskList.
     * @return String to notify user of successful command execution.
     * @throws DukeException In case of Duke related errors.
     * @throws IOException In case of invalid file directory.
     */
    public abstract String execute(TaskList tl) throws DukeException, IOException;

    /**
     * Returns whether this command is a ByeCommand.
     *
     * @return Whether command is a ByeCommand.
     */
    public abstract boolean getIsBye();
}
