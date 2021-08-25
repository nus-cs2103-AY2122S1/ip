package commands;

import bot.Bot;
import bot.Ui;
import exceptions.InvalidTaskException;

/**
 * Command to delete a command from the bot
 */
public class DeleteCommand extends Command {

    @Override
    public void run(Bot bot, String[] args) {
        int index = Integer.parseInt(args[0]) - 1;
        if (index < 0 || index >= bot.taskList.get().size()) {
            throw new InvalidTaskException(Ui.ERROR_SIGNATURE + "This task does not exist in the task list!");
        }
        bot.taskList.removeTask(index);
    }

}
