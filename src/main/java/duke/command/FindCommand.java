package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * Represents a Command that finds a Task to the TaskList.
 * An FindCommand consists of a keyword.
 *
 */

public class FindCommand extends Command {

    private final String keyword;

    /**
     * Creates a FindCommand Object.
     * @param keyword keyword to find tasks.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList filteredTasks = new TaskList();
        for (int i = 0; i < tasks.getSize(); i++) {
            Task toFilter = tasks.get(i);
            if (toFilter.toString().contains(keyword)) {
                filteredTasks.add(toFilter);
            }
        }
        return ui.printList(filteredTasks);
    }
}
