package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Abstract Class to encapsulate a Command
 */
public abstract class Command {
    /**
     * Execute the command
     *
     * @param tasks    the TaskList
     * @param ui       the Ui
     * @param storage  the data source
     *
     * @return         string stating the command result
     */

    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
    /**
     * Check if the command is an ExitCommand
     *
     * @return           boolean stating if command is ExitCommand
     */
    public abstract boolean isExit();
}
