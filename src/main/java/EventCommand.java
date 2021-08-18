public class EventCommand implements Command{

  @Override
  public void run(Bot bot, String[] args) {
    String[] splitArgs = args[0].split("/at", 2);
    Task eventTask = new EventTask(splitArgs[0], splitArgs[1]);
    bot.addTask(eventTask);
  }

}
