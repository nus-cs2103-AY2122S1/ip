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
    public String[] run(Bot bot, String[] args) throws InvalidArgumentsException {
        validateArgs(args);
        Task todoTask = new TodoTask(args[0]);
        return bot.getTaskList().addTask(todoTask);
    }

    /**
     * Validate command arguments
     *
     * @param args command args
     * @throws InvalidArgumentsException invalid command arguments
     */
    public void validateArgs(String[] args) throws InvalidArgumentsException {
        if (args[0].equals("")) {
            throw new InvalidArgumentsException(Ui.ERROR_SIGNATURE + "decription cannot b emtee.");
        }
    }

}
