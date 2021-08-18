/**
 * The OutputManager handles bot output messages to console
 */
public class OutputManager {

  public static String responseBlockMargin = "    "; 
  public static String textBlockMargin = " ";
  public static String divider = "_________________________________________________";
  public static String[] startMessages = {
    "Hello I'm Duke",
    "     _       _        ",
    "  __| |_   _| | _____ ",
    " / _` | | | | |/ / _ \\",
    "| (_| | |_| |   <  __/",
    " \\__,_|\\__,_|_|\\_\\___|",
    "What can I do for you?"
  };
  public static String[] endMessages = {
    "Bye. Hope to see you again!"
  };

  public OutputManager() {}

  /**
   * Prints welcome message dialogue
   */
  public void printWelcome() {
    this.print(startMessages);
  }

  /**
   * Prints goodbye message dialogue
   */
  public void printGoodbye() {
    this.print(endMessages);
  }

  /**
   * Prints given input string in the bot dialogue
   * 
   * @param input string to print in dialogue
   */
  public void printEcho(String input) {
    this.print(new String[] { input });
  }

  /**
   * Prints given messages in sequence in the bot
   * dialogue
   * 
   * @param messages string messages to print in 
   * dialogue
   */
  public void print(String[] messages) {
    System.out.println(responseBlockMargin + divider);
    for (String message: messages) {
      System.out.println(responseBlockMargin + textBlockMargin + message);
    }
    System.out.println(responseBlockMargin + divider);
  }
  
}
