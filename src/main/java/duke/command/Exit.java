package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents a type of Command that exits the program.
 */
public class Exit extends Command {
    /**
     * Performs the exiting of the program and printing the exit message.
     *
     * @param tasks The tasklist.
     * @param ui The ui.
     * @param storage The storage.
     */
    public String exec(TaskList tasks, Ui ui, Storage storage) {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Returns a boolean that indicates if this is an Exit.
     *
     * @return Whether this is an Exit.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
