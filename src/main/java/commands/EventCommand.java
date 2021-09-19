package commands;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import bot.Bot;
import bot.Ui;
import exceptions.InvalidArgumentsException;
import tasks.EventTask;
import tasks.Task;

/**
 * Command for adding an event task to the bot
 */
public class EventCommand extends Command {

    @Override
    public String[] run(Bot bot, String[] args) throws InvalidArgumentsException {
        String[] splitArgs = args[0].split("/at", 2);
        validateArgs(splitArgs);

        LocalDateTime taskTime = LocalDateTime.parse(splitArgs[1].trim(), Task.INPUT_TIME_FORMAT);
        Task eventTask = new EventTask(splitArgs[0], taskTime);
        return bot.getTaskList().addTask(eventTask);
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
        } else if (args.length < 2) {
            throw new InvalidArgumentsException(Ui.ERROR_SIGNATURE + "niid /at arguments... and moni for colleg");
        }
        try {
            LocalDateTime.parse(args[1].trim(), Task.INPUT_TIME_FORMAT);
        } catch (DateTimeParseException e) {
            throw new InvalidArgumentsException(Ui.ERROR_SIGNATURE + "Datetime mus be in DD/MM/YYYY HHMM formatz");
        }
    }

}
