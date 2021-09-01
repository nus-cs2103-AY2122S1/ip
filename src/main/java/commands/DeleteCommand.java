package commands;

import bot.Bot;
import bot.Ui;
import exceptions.InvalidTaskException;

/**
 * Command to delete a command from the bot
 */
public class DeleteCommand extends Command {

    @Override
    public String[] run(Bot bot, String[] args) {
        int index = Integer.parseInt(args[0]) - 1;
        if (index < 0 || index >= bot.getTaskList().get().size()) {
            throw new InvalidTaskException(Ui.ERROR_SIGNATURE + "This task does not exist in the task list!");
        }
        return bot.getTaskList().removeTask(index);
    }

}
