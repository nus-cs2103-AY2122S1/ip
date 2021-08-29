package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

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
     * @throws DukeException
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        ui.invalidUserInput();
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
