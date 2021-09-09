package commands;

import bot.Bot;
import bot.Ui;
import exceptions.InvalidArgumentsException;
import exceptions.InvalidTaskException;

/**
 * Command to delete a command from the bot
 */
public class DeleteCommand extends Command {

    @Override
    public String[] run(Bot bot, String[] args) throws InvalidTaskException {
        int index = Integer.parseInt(args[0]) - 1;
        validateArgs(index, bot);

        return bot.getTaskList().removeTask(index);
    }

    /**
     * Validate command arg
     *
     * @param arg command argument
     * @param bot Bot in context
     * @throws InvalidTaskException invalid command task
     */
    public void validateArgs(int arg, Bot bot) throws InvalidTaskException {
        if (arg < 0 || arg >= bot.getTaskList().size()) {
            throw new InvalidTaskException(Ui.ERROR_SIGNATURE + "This task does not exist in the task list!");
        }
    }

}
