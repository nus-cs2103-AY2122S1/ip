package duke.command;

import duke.exception.DukeException;

/**
 * CS2103T Individual Project AY 21/22 Sem 1
 * Project Duke
 *
 *
 * Description:
 * Abstract class that encapsulates a command inputted by the user
 *
 * @author Keith Tan
 */
public abstract class Command {

    /**
     * Returns a boolean of whether current command is the 'bye' command
     *
     * @return boolean returns boolean of whether current command is 'bye' command
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the command and runs any methods that is related to the command.
     *
     * @throws DukeException throws an exception if any Duke Error occurs while running
     *                       the associated methods
     */
    public abstract String execute() throws DukeException;

}
