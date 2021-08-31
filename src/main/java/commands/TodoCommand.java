package commands;

import bot.Bot;
import bot.Ui;
import exceptions.InvalidArgumentsException;
import tasks.Task;
import tasks.TodoTask;

/**
 * Command for adding a to-do task to the bot
 */
public class TodoCommand extends Command {

    @Override
    public void run(Bot bot, String[] args) {
        if (args[0].equals("")) {
            throw new InvalidArgumentsException(Ui.ERROR_SIGNATURE + "The description of a todo cannot be empty.");
        }
        Task todoTask = new TodoTask(args[0]);
        bot.getTaskList().addTask(todoTask);
    }

}
