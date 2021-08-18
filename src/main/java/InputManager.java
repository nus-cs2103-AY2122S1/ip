import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * The InputManager handles reading user input/commands
 */
public class InputManager {
  
  public InputManager() {}

  /**
   * Gets and returns user input
   * 
   * @return input string entered by user
   */
  public String getInput() {
    try {
      BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
      return reader.readLine();
    } catch (IOException error) {
      return "";
    }
  }

}
