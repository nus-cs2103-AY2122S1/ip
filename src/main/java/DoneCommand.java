public class DoneCommand implements Command {

  @Override
  public void run(Bot bot, String[] args) {
    String index = args[0].split(" ")[1];
    Task task = bot.getTaskAt(Integer.parseInt(index)-1);
    task.markDone();
    bot.printOutput(new String[] {
      "Nice! I've marked this task as done:",
      "  " + task.toString()
    });
  }
  
}
