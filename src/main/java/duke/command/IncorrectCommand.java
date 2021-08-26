package duke.command;
import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

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
     * When user input incorrect command, the programme will quit.
     *
     * @return False.
     */
    @Override
    public boolean isExist() {
        return false;
    }

    /**
     * Method to execute command.
     *
     * @param tasks The list of tasks in the current programme.
     * @param ui The user interface.
     * @param storage Handles interaction with the file.
     * @throws DukeException All exceptions related to Duke.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
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

