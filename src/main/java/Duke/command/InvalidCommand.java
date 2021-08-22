package Duke.command;

import Duke.exception.DukeException;
import Duke.exception.InvalidCommandException;

import Duke.util.Tasklist;
import Duke.util.Ui;
import Duke.util.Store;

/**
 * CS2103T Individual Project AY 21/22 Sem 1
 * Project Duke
 *
 * Current Progress: A-MoreOOP. Use More OOP
 *
 * Description:
 * Class that encapsulates an invalid command inputted by the user
 *
 * @author Keith Tan
 */
public class InvalidCommand extends Command {

    public static final String COMMAND_WORD = "error";

    public InvalidCommand() {

    }

    /**
     * Alerts the user that an invalid command has been inputted
     *
     * @param list Tasklist of current tasks
     * @param ui Ui which prints any successful message from the associated methods
     * @param storage Current Storage of DUke
     * @throws DukeException throws an exception if any Duke Error occurs while running
     *                       the associated methods
     */
    @Override
    public void execute(Tasklist list, Ui ui, Store storage) throws DukeException {
        throw new InvalidCommandException();

    }

}
