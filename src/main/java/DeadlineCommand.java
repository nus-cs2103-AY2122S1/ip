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
    Task deadlineTask = new DeadlineTask(splitArgs[0], splitArgs[1]);
    bot.addTask(deadlineTask);
  }
  
}
