package commands;

import bot.Bot;
import tasks.GeneralTask;
import tasks.Task;

/**
 * Command to add a task to the bot
 */
public class AddTaskCommand extends Command {

    @Override
    public String[] run(Bot bot, String[] args) {
        Task generalTask = new GeneralTask(args[0]);
        return bot.getTaskList().addTask(generalTask);
    }

}
