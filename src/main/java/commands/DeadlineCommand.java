package commands;

import tasks.DeadlineTask;
import tasks.Task;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import bot.Bot;
import bot.OutputManager;
import exceptions.InvalidArgumentsException;

public class DeadlineCommand implements Command {

  @Override
  public void run(Bot bot, String[] args) {
    if (args[0].equals("")) {
      throw new InvalidArgumentsException(OutputManager.ERROR_SIGNATURE + "The description of a deadline cannot be empty.");
    }
    String[] splitArgs = args[0].split("/by", 2);
    if (splitArgs.length < 2) {
      throw new InvalidArgumentsException(OutputManager.ERROR_SIGNATURE + "Deadlines require /by arguments");
    }
    
    LocalDateTime taskTime = LocalDateTime.parse(splitArgs[1].trim(), Task.INPUT_TIME_FORMAT);
    Task deadlineTask = new DeadlineTask(splitArgs[0], taskTime);
    bot.addTask(deadlineTask);
  }

}
