package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.task.TaskList;
import duke.Ui;

/**
 * Represents a command
 */
public class Command {

    /**
     * Returns true if given command is ByeCommand,
     * false otherwise.
     *
     * @return true if given command is ByeCommand,
     * false otherwise.
     */
    public boolean isExit() {
        return this instanceof ByeCommand;
    }

    /**
     * Executes command with given TaskList, Ui and Storage.
     *
     * @param taskList List of tasks.
     * @param ui User interface.
     * @param storage Storage of tasks.
     * @throws DukeException
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        throw new DukeException("Not supposed to happen!");
    }
}
