package commands;

import java.time.LocalDateTime;
import java.util.Arrays;

import bot.Bot;
import bot.Ui;
import exceptions.InvalidArgumentsException;
import tasks.DeadlineTask;
import tasks.Task;


/**
 * Command for adding a deadline task to the bot
 */
public class DeadlineCommand extends Command {

    @Override
    public String[] run(Bot bot, String[] args) {
        if (args[0].equals("")) {
            throw new InvalidArgumentsException(Ui.ERROR_SIGNATURE + "The description of a deadline cannot be empty.");
        }
        String[] splitArgs = args[0].split("/by", 2);
        if (splitArgs.length < 2) {
            throw new InvalidArgumentsException(Ui.ERROR_SIGNATURE + "Deadlines require /by arguments");
        }

        LocalDateTime taskTime = LocalDateTime.parse(splitArgs[1].trim(), Task.INPUT_TIME_FORMAT);
        Task deadlineTask = new DeadlineTask(splitArgs[0], taskTime);
        return bot.getTaskList().addTask(deadlineTask);
    }

}
