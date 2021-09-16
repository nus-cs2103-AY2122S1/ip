package duke.command;
import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

/**
 * Command to signal for the system to exit.
 */
public class ExitCommand extends Command {

    /**
     * Exits the program.
     *
     * @param ls Current list.
     * @param ui Current Ui.
     * @param storage Current version of the saved tasks in the hard disk.
     * @throws DukeException If user input is invalid.
     */
    @Override
    public String execute(TaskList ls, Ui ui, Storage storage) throws DukeException {
        System.exit(0);
        return ui.goodbye();
    }

    /**
     * Signals to the system that the command is an exit command.
     *
     * @return True.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
