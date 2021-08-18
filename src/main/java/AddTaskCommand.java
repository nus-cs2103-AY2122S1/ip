public class AddTaskCommand implements Command {

  @Override
  public void run(Bot bot, String[] args) {
    Task generalTask = new GeneralTask(args[0]);
    Boolean success = bot.addTask(generalTask);
    bot.printOutput(new String[] { 
      success 
       ? String.format("added: %s", args[0]) 
       : "Task list capacity reached"
    });
  }
  
}
