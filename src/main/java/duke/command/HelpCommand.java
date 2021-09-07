package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;


/**
 * This class handles command that asks for help.
 */
public class HelpCommand extends Command {
    public static final String COMMAND_WORD = "help";

    /**
     * Constructor.
     */
    public HelpCommand() {

    }

    /**
     * Method to execute command.
     *
     * @param taskList The list of tasks in the current programme.
     * @param ui The user interface.
     * @param storage Handles interaction with the file.
     * @return Help text.
     * @throws DukeException All exceptions related to Duke.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        return Ui.HELP_TEXT;
    }
}
