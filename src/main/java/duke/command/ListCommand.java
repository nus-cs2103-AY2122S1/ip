package duke.command;
import duke.Ui;
import duke.Storage;
import duke.DukeException;
import duke.task.TaskList;

/**
 * Command to print current list.
 */
public class ListCommand extends Command {

    /**
     * Prints current list.
     *
     * @param ls Current list.
     * @param ui Current Ui.
     * @param storage Current version of the saved tasks in the hard disk.
     * @throws DukeException If user input is invalid.
     */
    @Override
    public void execute(TaskList ls, Ui ui, Storage storage) throws DukeException {
        ui.printTaskList(ls);
    }

    /**
     * Signals to the system that the command is not an exit command.
     *
     * @return False.
     */
    @Override
    public boolean isExit() {
        return false;
    }

}
