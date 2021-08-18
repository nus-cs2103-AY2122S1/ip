public class TodoCommand implements Command {

  @Override
  public void run(Bot bot, String[] args) {
    Task todoTask = new TodoTask(args[0]);
    bot.addTask(todoTask);
  }
  
}
