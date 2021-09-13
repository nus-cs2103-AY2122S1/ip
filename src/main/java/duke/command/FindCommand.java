package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * Represents a Command that finds a Task in the TaskList.
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
        filterTasks(tasks, filteredTasks);
        return ui.printList(filteredTasks);
    }

    private void filterTasks(TaskList tasks, TaskList filteredTasks) {
        for (int i = 0; i < tasks.getSize(); i++) {
            Task toFilter = tasks.get(i);
            addTasks(filteredTasks, toFilter);
        }
    }

    private void addTasks(TaskList filteredTasks, Task toFilter) {
        if (toFilter.toString().contains(keyword)) {
            filteredTasks.add(toFilter);
        }
    }
}
