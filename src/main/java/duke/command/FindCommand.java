package duke.command;

import java.util.ArrayList;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

public class FindCommand extends Command {
    /**
     * Constructor for a {@code DeleteCommand}
     *
     * @param keyword Search term.
     */
    public FindCommand(String keyword) {
        super(keyword);
    }

    /**
     * Searches for a tasks based on the given keyword and displays the filtered tasks in the terminal.
     *
     * @param tasks   the tasklist to be modified.
     * @param ui      responsible for printing to the terminal.
     * @param storage stores all the tasks.
     *
     * @return String message to be displayed.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> filteredTasks = tasks.searchTasks(args[0]);
        return ui.showFilteredTasks(filteredTasks);
    }
}
