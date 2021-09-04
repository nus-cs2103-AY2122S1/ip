package bot.commands;

import java.util.List;

import bot.tasks.Task;
import bot.utility.TaskList;

/**
 * Represents a command to show the list of tasks.
 */
public class ListCommand extends Command {

    /**
     * Executes the Command and returns a String.
     *
     * @return A String to show to the user after execution of the Command.
     */
    @Override
    public String execute() {
        message = new StringBuilder();
        message.append("\n\t Here are the tasks in your list:");
        List<Task> tasks = TaskList.showTasks();
        for (int i = 0; i < tasks.size(); i++) {
            message.append("\n\t ").append(i + 1).append(". ").append(tasks.get(i));
        }
        return message.toString();
    }
}
