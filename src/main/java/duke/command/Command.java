package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Abstract Command class that executes a series of operations depending on the type of Command.
 *
 */
public abstract class Command {
    /**
     * Executes a series of operations .
     *
     * Operations include: Printing to screen, Updating Hard disk and updating Task List.
     *
     * @param taskList
     * @param ui
     * @param storage
     * @throws DukeException
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;

    /**
     * Returns a boolean of whether this Command is an Exit Command.
     *
     * @return boolean
     */
    public abstract boolean isExit();
}
