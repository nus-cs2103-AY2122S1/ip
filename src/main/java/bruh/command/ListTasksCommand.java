package bruh.command;

import bruh.storage.Storage;
import bruh.tasklist.TaskList;
import bruh.ui.Ui;

/**
 * Represents a command to list all tasks in the task list.
 */
public class ListTasksCommand extends Command {
    @Override
    public String runAndGenerateDescription(TaskList taskList, Ui ui, Storage storage) {
        return taskList.listTasks();
    }
}
