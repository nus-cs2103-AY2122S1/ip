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
     * Method to determine whether programme stops after the current comment.
     *
     * @return False if programme should stop.
     */
    public boolean isExist() {
        return true;
    }

    /**
     * Method to execute command.
     *
     * @param tasks The list of tasks in the current programme.
     * @param ui The user interface.
     * @param storage Handles interaction with the file.
     * @throws DukeException All exceptions related to Duke.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
    }
}
