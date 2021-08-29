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
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        List<Task> filteredList = tasks.filter(this.searchQuery);
        System.out.println("    * * * * * * * * * * * * * * * * * * * *");
        if (filteredList.size() > 0) {
            System.out.println("    Here are the matching tasks in your list: ");
            for (int i = 0; i < filteredList.size(); i++) {
                String item = "    " + (i + 1) + "." + filteredList.get(i);
                System.out.println(item);
            }
        } else {
            System.out.println("    Oops... There is no matching task in your list! Please try again.");
        }
        System.out.println("    * * * * * * * * * * * * * * * * * * * *\n");
    }
}
