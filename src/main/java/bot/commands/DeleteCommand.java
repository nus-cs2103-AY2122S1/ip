package bot.commands;

import java.util.List;

import bot.tasks.Task;
import bot.utility.Logger;
import bot.utility.TaskList;

public class DeleteCommand extends Command {
    private static final String DELETE_FORMAT = "\n\t Noted. I've removed this task:\n\t\t%s";
    private final int index;
    public DeleteCommand(String indexString) {
        this.index = Integer.parseInt(indexString);
    }
    @Override
    public String execute() {
        List<Task> tasks = TaskList.showTasks();
        Task task = tasks.remove(index);
        Logger.write(tasks);
        return String.format(DELETE_FORMAT, task) + String.format(INFORM_FORMAT, tasks.size());
    }
}
