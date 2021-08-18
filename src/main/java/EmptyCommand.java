public class EmptyCommand implements Command {

  @Override
  public void run(Bot bot, String[] args) {
    bot.printOutput(new String[]{
      OutputManager.ERROR_SIGNATURE + "I'm sorry, but I don't know what that means :-("
    });
  }
  
}
