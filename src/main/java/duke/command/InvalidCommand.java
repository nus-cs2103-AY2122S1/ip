package duke.command;

import duke.exception.DukeException;
import duke.exception.DukeInvalidCommandException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * InvalidCommand class represents commands that are invalid.
 *
 * @author Chng Zi Hao
 */
public class InvalidCommand extends Command {

    /**
     * Deletes task from the task list.
     *
     * @param taskList   TaskList of Duke.
     * @param ui      The user interface.
     * @param storage Storage for Duke.
     * @return Message to be shown to user.
     * @throws DukeException If command is invalid.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        throw new DukeInvalidCommandException();
    }
}
