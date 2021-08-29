package duke.commands;

import java.util.List;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;


/**
 * This class encapsulates the find command.
 */
public class FindCommand extends Command {
    private String query;

    /**
     * Constructor of the FindCommand class.
     *
     * @param query The query to be used to find matching tasks.
     */
    public FindCommand(String query) {
        this.query = query;
    }

    /**
     * Executes the find command.
     *
     * @param tasks The list of tasks.
     * @param ui The UI of the Duke app.
     * @param storage The storage manager for the Duke app.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        List<Task> matches = tasks.find(this.query);
        ui.printMatches(matches);
    }
}
