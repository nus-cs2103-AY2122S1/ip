package duke.command;

import duke.TaskManager;

/**
 * Represents the command "list" to list the tasks in given taskManager in numbered list format.
 */
public class ListCommand extends Command {
    @Override
    public String execute(TaskManager taskManager) {
        return String.format("Here are the tasks in your list:\n%s", taskManager.listTasks());
    }
}
