public class EmptyCommand implements Command {

  @Override
  public void run(Bot bot, String[] args) {
    bot.printOutput(new String[]{
      String.format("The command %s doesn't exist", args[0])
    });
  }
  
}
