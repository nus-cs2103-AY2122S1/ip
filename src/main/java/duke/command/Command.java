package duke.command;

import duke.Archive;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.errors.DukeException;


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
     * @param archive
     * @throws DukeException
     */
    public abstract String execute(TaskList taskList, Ui ui, Storage storage, Archive archive) throws DukeException;

    /**
     * Returns a boolean of whether this Command is an Exit Command.
     *
     * @return boolean
     */
    public abstract boolean isExit();
}
