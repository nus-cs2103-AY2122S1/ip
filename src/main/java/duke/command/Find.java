package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents a type of Command that finds tasks in a tasklist based on a given searchTerm.
 */
public class Find extends Command {
    private String searchTerm;

    /**
     * Constructs a Command Find from a given search term.
     * @param searchTerm The given search term.
     */
    public Find(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    /**
     * Performs the search based on the search term and displays the results using List Command.
     * @param tasks The tasklist.
     * @param ui The ui.
     * @param storage The storage.
     */
    public String exec(TaskList tasks, Ui ui, Storage storage) {
        TaskList temp = new TaskList();
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).toString().toLowerCase().contains(this.searchTerm.toLowerCase())) {
                temp.add(tasks.get(i));
            }
        }
        return "Here are the matching tasks in your list:\n" + new List().exec(temp, ui, storage);
    }
}
