package bot.commands;

import java.util.List;

import bot.tasks.Task;
import bot.utility.TaskList;

/**
 * Represents a command to show the list of tasks.
 */
public class ListCommand extends Command {
    private static final String LIST_TASK_FORMAT = "\n" + TAB_SPACES + " %d. %s";
    private static final String EMPTY_LIST_MESSAGE = "\nOh, it seems there are no tasks. You must be very lucky today!";
    /**
     * Executes the Command and returns a String.
     *
     * @return A String to show to the user after execution of the ListCommand.
     */
    @Override
    public String execute() {
        List<Task> tasks = TaskList.showTasks();
        if (tasks.size() == 0) {
            return EMPTY_LIST_MESSAGE;
        }
        message = new StringBuilder();
        message.append("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            message.append(String.format(LIST_TASK_FORMAT, (i + 1), tasks.get(i)));
        }
        return message.toString();
    }
}
