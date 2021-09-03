package duke.command;

import duke.exception.DukeException;

/**
 * CS2103T Individual Project AY 21/22 Sem 1
 * Project Duke
 *
 *
 * Description:
 * Class that encapsulates the bye command inputted by the user
 *
 * @author Keith Tan
 */
public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "exit";

    /**
     * Constructor for ExitCommand
     * Initializes an exit command to signal Duke should be ended
     */
    public ExitCommand() {

    }

    /**
     * Returns a boolean of whether current command is the 'bye' command
     *
     * @return boolean returns boolean of whether current command is 'bye' command
     */
    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * Executes the exit command. Returns the goodbye message
     *
     * @throws DukeException throws an exception if any Duke Error occurs while running
     *                       the associated methods
     */
    @Override
    public String execute() throws DukeException {

        String farewellMessage = "Bye. Hope to see you again soon!";
        return farewellMessage;

    }
}
