package commands;

import tasks.GeneralTask;
import tasks.Task;
import bot.Bot;
import bot.Ui;

public class AddTaskCommand extends Command {

  @Override
  public void run(Bot bot, String[] args) {
    Task generalTask = new GeneralTask(args[0]);
    Boolean success = bot.taskList.addTask(generalTask);
    Ui.print(new String[] { 
      success 
       ? String.format("added: %s", args[0]) 
       : "Task list capacity reached"
    });
  }
  
}
