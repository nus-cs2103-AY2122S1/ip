package bot;

import java.util.Scanner;

import commands.Command;

/**
 * The InputManager handles reading user input/commands
 */
public class Parser {
  
  private static Scanner reader;

  static {
    reader = new Scanner(System.in);
  }

  /**
   * Gets and returns user input
   * 
   * @return input string entered by user
   */
  public static String getInput() {
    return reader.nextLine();
  }

  public static Command getCommand() {
    String[] input = getInput().split(" ", 2);
    Command cmd = CommandType.getCommandFromName(input[0]);
    cmd.setArgs(new String[]{ input.length >=2 ? input[1] : "" });
    return cmd;
  }

  /**
   * Close scanner for cleanup
   */
  public static void closeScanner() {
    reader.close();
  }

}
