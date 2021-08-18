public class EventCommand implements Command{

  @Override
  public void run(Bot bot, String[] args) {
    if (args[0].equals("")) {
      throw new InvalidArgumentsException(OutputManager.ERROR_SIGNATURE + "The description of an event cannot be empty.");
    }
    String[] splitArgs = args[0].split("/at", 2);
    if (splitArgs.length < 2) {
      throw new InvalidArgumentsException(OutputManager.ERROR_SIGNATURE + "Events require /at arguments");
    }
    Task eventTask = new EventTask(splitArgs[0], splitArgs[1]);
    bot.addTask(eventTask);
  }

}
