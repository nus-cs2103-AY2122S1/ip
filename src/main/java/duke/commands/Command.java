package duke.commands;
import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * The Command class represents actions entered by the user.
 */
public abstract class Command {

    /**
     * Executes the given command.
     *
     * @param taskList The current TaskList being used.
     * @param ui The current Ui being used.
     * @param storage The current Storage being used.
     * @throws DukeException If there is invalid command entered.
     */
    public abstract String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;

    public boolean isExit() {
        return false;
    }

}
