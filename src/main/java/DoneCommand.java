public class DoneCommand implements Command {

  @Override
  public void run(Bot bot, String[] args) {
    int index = Integer.parseInt(args[0]) - 1;
    if (index < 0 || index >= bot.getTaskList().size()) {
      throw new InvalidTaskException(OutputManager.ERROR_SIGNATURE + "This task does not exist in the task list!");
    }
    Task task = bot.getTaskAt(index);
    task.markDone();
    bot.printOutput(new String[] {
      "Nice! I've marked this task as done:",
      OutputManager.TEXT_BLOCK_MARGIN + task.toString()
    });
  }
  
}
