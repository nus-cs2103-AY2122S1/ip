package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.dukeexception.NoListException;

/**
 * Represents a general Command.
 */
public abstract class Command {
    /**
     * Shows the method signature of exec method for concrete classes to implement.
     *
     * @param tasks The task list.
     * @param ui The ui.
     * @param storage The storage.
     * @throws NoListException If there is no list to be loaded.
     */
    public abstract String exec(TaskList tasks, Ui ui, Storage storage) throws NoListException;

    /**
     * Returns a boolean that indicates if this is an Exit.
     *
     * @return Whether this is an Exit.
     */
    public boolean isExit() {
        return false;
    }
}
