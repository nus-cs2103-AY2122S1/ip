package duke.command;

import java.util.List;

import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

public class FindCommand extends Command {

    private String searchQuery;

    /**
     * Constructs a FindCommand object.
     *
     * @param searchQuery Keyword to search for in a task's description.
     */
    public FindCommand(String searchQuery) {
        super(false);
        this.searchQuery = searchQuery;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        assert tasks != null : "Task list is not initialized";
        List<Task> filteredList = tasks.filter(this.searchQuery);
        String msg;
        if (filteredList.size() > 0) {
            msg = "About " + filteredList.size() + " results: \n";
            for (int i = 0; i < filteredList.size(); i++) {
                String item = "    " + (i + 1) + "." + filteredList.get(i) + "\n";
                msg += item;
            }
        } else {
            msg = "Oops... There is no matching task in your list! Please try again.";
        }
        return msg;

    }

}
