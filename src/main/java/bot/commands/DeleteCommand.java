package bot.commands;

import java.util.List;

import bot.tasks.Task;
import bot.utility.Logger;
import bot.utility.TaskList;

/**
 * Represents a command to delete tasks.
 */
public class DeleteCommand extends Command {
    private static final String DELETE_FORMAT = "\n\t Noted. I've removed this task:\n\t\t%s";
    private final int index;

    /**
     * Returns a DeleteCommand with the specified index which is given as a string.
     *
     * @param indexString A String containing an integer value;
     */
    public DeleteCommand(String indexString) {
        this.index = Integer.parseInt(indexString);
    }

    /**
     * Executes the Command and returns a String.
     *
     * @return A String to show to the user after execution of the Command.
     */
    @Override
    public String execute() {
        List<Task> tasks = TaskList.showTasks();
        Task task = tasks.remove(index);
        Logger.write(tasks);
        return String.format(DELETE_FORMAT, task) + String.format(INFORM_FORMAT, tasks.size());
    }
}
