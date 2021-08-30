package duke.command;

import duke.exception.DukeException;
import duke.exception.InvalidCommandException;

/**
 * CS2103T Individual Project AY 21/22 Sem 1
 * Project Duke
 *
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
     * @throws DukeException throws an exception if any Duke Error occurs while running
     *                       the associated methods
     */
    @Override
    public String execute() throws DukeException {
        throw new InvalidCommandException();

    }

}
