package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;


/**
 * The general command class.
 */
public class Command {

    /**
     * Constructor for Command.
     */
    public Command() {
    }

    /**
     * Method to execute command.
     *
     * @param tasks The list of tasks in the current programme.
     * @param ui The user interface.
     * @param storage Handles interaction with the file.
     * @return Response message of that command.
     * @throws DukeException All exceptions related to Duke.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        return "null";
    }
}
