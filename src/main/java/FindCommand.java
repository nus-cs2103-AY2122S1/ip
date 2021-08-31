package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.Storage;
import duke.task.Task;

/**
 * Represents a Command that finds a Task to the TaskList.
 * An FindCommand consists of a keyword.
 *
 */

public class FindCommand extends Command{

    private final String keyword;

    /**
     * Creates a FindCommand Object.
     * @param keyword keyword to find tasks.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList filteredTasks = new TaskList();
        for (int i = 0; i < tasks.size(); i++) {
            Task toFilter = tasks.get(i);
            if (toFilter.toString().contains(keyword)) {
                filteredTasks.add(toFilter);
            }
        }
        ui.printList(filteredTasks);
    }
}
