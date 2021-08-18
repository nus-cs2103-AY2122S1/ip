import java.util.Scanner;

/**
 * The InputManager handles reading user input/commands
 */
public class InputManager {
  
  private Scanner reader;

  public InputManager() {
    this.reader = new Scanner(System.in);
  }

  /**
   * Gets and returns user input
   * 
   * @return input string entered by user
   */
  public String getInput() {
    return reader.nextLine();
  }

  public void closeScanner() {
    reader.close();
  }

}
