package duke.command;

import duke.exception.DukeException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Command class represents a executable command that Duke can handle.
 *
 * @author Chng Zi Hao
 */
public abstract class Command {
    public abstract String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;

    /**
     * Returns a boolean on whether the command is an exit command.
     *
     * @return True if the command is an exit command, False otherwise.
     */
    public boolean isExit() {
        return false;
    }
}
