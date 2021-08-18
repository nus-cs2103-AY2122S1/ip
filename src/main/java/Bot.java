import java.util.List;
import java.util.ArrayList;

/**
 * Bot handles the bot's REPL cycle
 */
public class Bot {
  private final int MAX_TASKS = 100;
  private InputManager inputManager;
  private OutputManager outputManager;
  private List<Task> taskList;
  private Boolean running;

  public Bot() {
    this.inputManager = new InputManager();
    this.outputManager = new OutputManager();
    this.taskList = new ArrayList<>();
  }
  
  /**
   * Starts the task bot
   */
  public void start() {
    outputManager.printWelcome();
    this.running = true;
    while (this.running) {
      String[] input = inputManager.getInput().split(" ", 2);
      Command cmd = CommandType.getCommandFromName(input[0]);
      try {
        cmd.run(this, new String[]{ input.length >=2 ? input[1] : "" });
      } catch (InvalidArgumentsException | InvalidTaskException e) {
        printOutput(new String[] {
          e.getMessage()
        });
      }
    }
    outputManager.printGoodbye();
    inputManager.closeScanner();
  }

  public void stop() {
    this.running = false;
  }

  //===========================
  //          Output
  //===========================

  public OutputManager getOutputManager() {
    return this.outputManager;
  }

  public void printOutput(String[] messages) {
    this.outputManager.print(messages);
  }


  //===========================
  //          Tasks
  //===========================

  public List<Task> getTaskList() {
    return this.taskList;
  }

  public Boolean addTask(Task newTask) {
    if (this.taskList.size() >= this.MAX_TASKS) {
      return false;
    }
    this.taskList.add(newTask);
    printOutput(new String[] { 
      "Got it. I've added this task:",
      OutputManager.TEXT_BLOCK_MARGIN + newTask.toString(),
      String.format("Now you have %d task(s) in the list", taskList.size())
    });
    return true;
  }

  public Boolean removeTask(int index) {
    if (index < 0 || index >= taskList.size()) {
      return false;
    }
    Task task = getTaskAt(index);
    this.taskList.remove(index);
    printOutput(new String[] { 
      "Noted. I've removed this task:",
      OutputManager.TEXT_BLOCK_MARGIN + task.toString(),
      String.format("Now you have %d task(s) in the list", taskList.size())
    });
    return true;
  }

  public Task getTaskAt(int index) {
    return this.taskList.get(index);
  }

}
