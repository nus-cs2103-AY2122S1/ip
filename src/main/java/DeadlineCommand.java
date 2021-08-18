public class DeadlineCommand implements Command {

  @Override
  public void run(Bot bot, String[] args) {
    String[] splitArgs = args[0].split("/by", 2);
    Task deadlineTask = new DeadlineTask(splitArgs[0], splitArgs[1]);
    bot.addTask(deadlineTask);
  }
  
}
