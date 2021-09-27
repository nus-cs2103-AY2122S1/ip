package duke.commands;

import duke.data.TaskList;
import duke.data.exception.DukeException;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Abstract class encapsulating all commands
 */
public abstract class Command {
    /**
     * Executes the given command and returns reply
     *
     * @param tasks the list of tasks
     * @param ui the user interface
     * @param storage the location of saved data
     * @return reply to the command
     * @throws DukeException
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
}
