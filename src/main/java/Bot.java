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
      System.out.println();
      String input = inputManager.getInput();
      Command cmd = CommandType.getCommandFromName(input.split(" ")[0]);
      cmd.run(this, new String[]{ input });
    }
    outputManager.printGoodbye();
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
    return true;
  }

  public Boolean removeTask(int index) {
    if (index < 0 || index >= taskList.size()) {
      return false;
    }
    this.taskList.remove(index);
    return true;
  }

  public Task getTaskAt(int index) {
    return this.taskList.get(index);
  }

}
