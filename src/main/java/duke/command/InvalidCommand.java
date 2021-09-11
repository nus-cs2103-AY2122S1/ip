package duke.command;

import duke.Archive;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.errors.DukeException;


/**
 * Type of Command that is called when user gives an invalid input.
 *
 */
public class InvalidCommand extends Command {

    /**
     * Constructor.
     */
    public InvalidCommand() {
    }

    /**
     * Executes operations to display error message to the user.
     *
     * @param taskList
     * @param ui
     * @param storage
     * @param archive
     * @throws DukeException
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage, Archive archive) throws DukeException {
        return ui.invalidUserInput();
    }

    /**
     * Not an Exit Command
     *
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
