package bruh.command;

import bruh.storage.Storage;
import bruh.tasklist.TaskList;
import bruh.ui.Ui;

/**
 * Represents a command to list all tasks which match a specified search term.
 */
public class ListTasksBySearchCommand extends ListTasksCommand {
    private final String searchTerm;

    public ListTasksBySearchCommand(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    @Override
    public String runAndGenerateDescription(TaskList taskList, Ui ui, Storage storage) {
        return taskList.filterBySearch(searchTerm).listTasks();
    }
}
