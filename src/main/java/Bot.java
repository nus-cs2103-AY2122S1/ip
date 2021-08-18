/**
 * Bot handles the bot's REPL cycle
 */
public class Bot {
  private InputManager inputManager;
  private OutputManager outputManager;

  public Bot() {
    this.inputManager = new InputManager();
    this.outputManager = new OutputManager();
  }
  
  /**
   * Starts the task bot
   */
  public void start() {
    outputManager.printWelcome();
    while (true) {
      System.out.println();
      String input = inputManager.getInput();
      if (input.equals("bye")) { break; }
      outputManager.printEcho(input);
    }
    outputManager.printGoodbye();
  }

}
