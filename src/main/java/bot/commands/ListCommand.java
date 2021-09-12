package bot.commands;

import java.util.List;

import bot.tasks.Task;
import bot.utility.TaskList;

/**
 * Represents a command to show the list of tasks.
 */
public class ListCommand extends Command {
    private static final String LIST_TASK_FORMAT = "\n" + TAB_SPACES + " %d. %s";

    /**
     * Executes the Command and returns a String.
     *
     * @return A String to show to the user after execution of the Command.
     */
    @Override
    public String execute() {
        message = new StringBuilder();
        message.append("Here are the tasks in your list:");
        List<Task> tasks = TaskList.showTasks();
        for (int i = 0; i < tasks.size(); i++) {
            message.append(String.format(LIST_TASK_FORMAT, (i + 1), tasks.get(i)));
        }
        return message.toString();
    }
}
