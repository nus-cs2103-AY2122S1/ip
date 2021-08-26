package duke.command;

import duke.data.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Encapsulates commands that can be executed by the duke program.
 *
 * @author Zhi Bin
 * @version Duke Level 9
 */
public abstract class DukeCommand {
    protected final Ui ui;
    protected final Storage storage;
    protected final TaskList list;

    /**
     * Constructor for a DukeCommand.
     *
     * @param ui      The Ui handler that handles the printing of message with respect to the command.
     * @param storage The storage handler that handles saving or loading data to local directory.
     * @param list    The TaskList handler that handles operation related to task.
     */
    public DukeCommand(Ui ui, Storage storage, TaskList list) {
        this.ui = ui;
        this.storage = storage;
        this.list = list;
    }

    /**
     * Executes the command.
     *
     * @throws DukeException When an error occurred.
     */
    public abstract void execute() throws DukeException;

    /**
     * Checks if the command is an Exit command.
     * Returns false by default, only overridden by
     * Exit class to return true.
     *
     * @return false
     */
    public boolean isExit() {
        return false;
    }
}
