package duke.command;

import duke.exception.DukeException;

import duke.util.Tasklist;
import duke.util.Ui;
import duke.util.Store;

/**
 * CS2103T Individual Project AY 21/22 Sem 1
 * Project Duke
 *
 *
 * Description:
 * Class that encapsulates the help command inputted by the user
 *
 * @author Keith Tan
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";

    public HelpCommand() {

    }

    /**
     * Executes the help command. Prints the help message for the user.
     *
     * @param list Tasklist of current tasks
     * @param ui Ui which prints any successful message from the associated methods
     * @param storage Current Storage of DUke
     * @throws DukeException throws an exception if any Duke Error occurs while running
     *                       the associated methods
     */
    @Override
    public void execute(Tasklist list, Ui ui, Store storage) throws DukeException {
        ui.printHelpMessage();

    }

}
