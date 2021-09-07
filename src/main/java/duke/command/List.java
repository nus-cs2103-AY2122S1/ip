package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents a type of Command that lists all the tasks in a tasklist.
 */
public class List extends Command {
    /**
     * Performs the listing of tasks.
     *
     * @param tasks The tasklist.
     * @param ui The ui.
     * @param storage The storage.
     */
    public String exec(TaskList tasks, Ui ui, Storage storage) {
        return tasks.showTask();
    }
}
