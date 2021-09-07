package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

/**
 * This class represents an EmptyCommand, when nothing should be done.
 * Main purpose of this class is so the default case is this, and the while loop does not terminate.
 */
public class EmptyCommand extends Command {
    /**
     * Does nothing.
     *
     * @param tasks task list
     * @param storage storage
     * @param ui ui
     * @return nothing.
     */
    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) {
        // Do nothing
        return null;
    }
}
