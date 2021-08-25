package commands;

import tasks.EventTask;
import tasks.Task;

import java.time.LocalDateTime;

import bot.Bot;
import bot.Ui;
import exceptions.InvalidArgumentsException;

public class EventCommand extends Command {

    @Override
    public void run(Bot bot, String[] args) {
        if (args[0].equals("")) {
            throw new InvalidArgumentsException(Ui.ERROR_SIGNATURE + "The description of an event cannot be empty.");
        }
        String[] splitArgs = args[0].split("/at", 2);
        if (splitArgs.length < 2) {
            throw new InvalidArgumentsException(Ui.ERROR_SIGNATURE + "Events require /at arguments");
        }

        LocalDateTime taskTime = LocalDateTime.parse(splitArgs[1].trim(), Task.INPUT_TIME_FORMAT);
        Task eventTask = new EventTask(splitArgs[0], taskTime);
        bot.taskList.addTask(eventTask);
    }

}
