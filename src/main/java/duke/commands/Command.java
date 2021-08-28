package duke.commands;

import duke.data.TaskList;
import duke.data.exception.DukeException;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Abstract class encapsulating all commands
 */
public abstract class Command {
    /** If program is to be exited after this command */
    protected boolean isExit = false;

    /**
     * Executes the given command
     * @param tasks the list of tasks
     * @param ui the user interface
     * @param storage the location of saved data
     * @throws DukeException
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
    
    public boolean isExit() {
        return this.isExit;
    } 
}
