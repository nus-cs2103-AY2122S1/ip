/**
 * The OutputManager handles bot output messages to console
 */
public class OutputManager {

  public static final String RESPONSE_BLOCK_MARGIN = "    "; 
  public static final String TEXT_BLOCK_MARGIN = " ";
  public static final String DIVIDER = "_________________________________________________";
  public static final String[] START_MESSAGES = {
    "Hello I'm Duke",
    "     _       _        ",
    "  __| |_   _| | _____ ",
    " / _` | | | | |/ / _ \\",
    "| (_| | |_| |   <  __/",
    " \\__,_|\\__,_|_|\\_\\___|",
    "What can I do for you?"
  };
  public static final String[] END_MESSAGES = {
    "Bye. Hope to see you again!"
  };

  public OutputManager() {}

  /**
   * Prints welcome message dialogue
   */
  public void printWelcome() {
    this.print(START_MESSAGES);
  }

  /**
   * Prints goodbye message dialogue
   */
  public void printGoodbye() {
    this.print(END_MESSAGES);
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
    System.out.println(RESPONSE_BLOCK_MARGIN + DIVIDER);
    for (String message: messages) {
      System.out.println(RESPONSE_BLOCK_MARGIN + TEXT_BLOCK_MARGIN + message);
    }
    System.out.println(RESPONSE_BLOCK_MARGIN + DIVIDER);
  }
  
}
