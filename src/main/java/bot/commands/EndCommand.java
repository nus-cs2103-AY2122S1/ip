package bot.commands;

import java.util.List;

import bot.tasks.Task;
import bot.utility.Logger;
import bot.utility.TaskList;

public class EndCommand extends Command {
    @Override
    public String execute() {
        List<Task> tasks = TaskList.showTasks();
        Logger.write(tasks);
        this.canExit = true;
        return "\n\t Peace out!";
    }
}
