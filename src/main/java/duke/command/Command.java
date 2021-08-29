package duke.command;

import duke.exception.DukeException;
import duke.util.Store;
import duke.util.Tasklist;
import duke.util.Ui;

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
     * @param list Tasklist of current tasks
     * @param ui Ui which prints any successful message from the associated methods
     * @param storage Current Storage of DUke
     * @throws DukeException throws an exception if any Duke Error occurs while running
     *                       the associated methods
     */
    public abstract void execute(Tasklist list, Ui ui, Store storage) throws DukeException;

}
