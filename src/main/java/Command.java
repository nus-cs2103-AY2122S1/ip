public interface Command {

  /**
   * Run the command
   * 
   * @param bot the bot that invokes the command
   * @param args list of argument strings
   */
  void run(Bot bot, String[] args);

}
