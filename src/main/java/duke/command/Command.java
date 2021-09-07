package duke.command;
import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

/**
 * Abstract class to encapsulate commands.
 */
public abstract class Command {


    /**
     * Executes the command.
     *
     * @param ls Current list.
     * @param ui Current Ui.
     * @param storage Current version of the saved tasks in the hard disk.
     * @throws DukeException If input is invalid.
     */
    public abstract String execute(TaskList ls, Ui ui, Storage storage) throws DukeException;

    /**
     * Signals to the system whether the command is an exit command.
     *
     * @return True if command is "bye".
     */
    public abstract boolean isExit();
}
