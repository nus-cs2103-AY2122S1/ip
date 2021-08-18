public class ByeCommand implements Command {

  @Override
  public void run(Bot bot, String[] args) {
    bot.stop();
  }
  
}
