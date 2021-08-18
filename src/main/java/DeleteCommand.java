public class DeleteCommand implements Command {

  @Override
  public void run(Bot bot, String[] args) {
    int index = Integer.parseInt(args[0]) - 1;
    if (index < 0 || index >= bot.getTaskList().size()) {
      throw new InvalidTaskException(OutputManager.ERROR_SIGNATURE + "This task does not exist in the task list!");
    }
    bot.removeTask(index);
  }
  
}
