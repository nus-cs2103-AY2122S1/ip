package duke.commands;

import duke.DukeException;
import duke.DukeStorage;
import duke.TaskList;
import duke.Ui;

/**
 * An abstract class that all commands inherit from
 *
 */
public abstract class Command {

    /**
     * Method to execute the command that is called
     *
     * @param taskList The list of tasks that is associated with the instance of Duke
     * @param ui The UI that is associated with the instance of Duke
     * @param storage The storage that is associated with the instance of Duke
     * @throws DukeException An error thrown if there are any issues while executing the command
     */
    public abstract void execute(TaskList taskList, Ui ui, DukeStorage storage) throws DukeException;

    /**
     * Method to return boolean depending on if Duke is to be exited
     *
     * @return boolean that returns true if Duke is to be exited and false if otherwise
     */
    public abstract boolean isExit();
}