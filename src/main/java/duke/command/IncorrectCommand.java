package duke.command;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import duke.exception.InvalidCommandException;

/**
 * This class handles all commands that are not formatted correctly.
 */
public class IncorrectCommand extends Command {

    /**
     * Constructor for IncorrectCommand.
     */
    public IncorrectCommand() {
    }

    /**
     * Method to execute command.
     *
     * @param tasks The list of tasks in the current programme.
     * @param ui The user interface.
     * @param storage Handles interaction with the file.
     * @return Notice on incorrect command.
     * @throws DukeException All exceptions related to Duke.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        throw new InvalidCommandException();
    }

    /**
     * Used for testing.
     *
     * @return A constant.
     */
    @Override
    public int hashCode() {
        return 1;
    }
}

