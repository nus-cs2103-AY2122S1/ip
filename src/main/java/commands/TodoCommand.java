package commands;

import tasks.Task;
import tasks.TodoTask;
import bot.Bot;
import bot.OutputManager;
import exceptions.InvalidArgumentsException;

public class TodoCommand implements Command {

  @Override
  public void run(Bot bot, String[] args) {
    if (args[0].equals("")) {
      throw new InvalidArgumentsException(OutputManager.ERROR_SIGNATURE + "The description of a todo cannot be empty.");
    }
    Task todoTask = new TodoTask(args[0]);
    bot.addTask(todoTask);
  }
  
}
