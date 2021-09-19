package duke;

import java.util.List;

/**
 * Represents a command to list all tasks
 */
public class ListCommand extends Command {

    /**
     * Executes <code>ListCommand</code>
     * @param tasks <code>TaskList</code> containing saved tasks
     * @param ui <code>Ui</code> responsible for user interactions
     * @param storage <code>Storage</code> responsible for saving tasks to drive
     * @return corresponding message listing all current tasks
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        List<Task> savedTasks = tasks.getTasks();
        String message = "";
        if (savedTasks.size() == 0) {
            return "No tasks in your list! Add one using todo, deadline or event!";
        }
        message += "Here are the tasks in your list:\n";
        for (int i = 0; i < savedTasks.size(); i++) {
            Task currTask = savedTasks.get(i);
            int index = i + 1;
            message += index + ". " + currTask + "\n";
        }
        return message;
    }
}
