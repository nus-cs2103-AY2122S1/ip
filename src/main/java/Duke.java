import java.util.Scanner;

public class Duke {

  private enum Command {
    BYE("bye");

    final String commandValue;

    private Command(String command) {
      this.commandValue = command;
    }

    private final String getCommand() {
      return this.commandValue;
    }
  }

  private static final String INDENTATION_4 = "    ";
  private static final String INDENTATION_5 = "     ";
  private static final String BREAK_LINE = "____________________________________________________________";
  private static final String[] GREETING = { "Hello! I'm Fergus' Chatbot", "What can I do for you?" };
  private static final String FAREWELL = "Bye. Hope to see you again soon!";

  private static String formatOutput(String inputText) {
    return INDENTATION_4 + BREAK_LINE + "\n" + INDENTATION_5 + inputText + "\n" + INDENTATION_4 + BREAK_LINE + "\n";
  }

  private static void print(String inputText) {
    System.out.println(formatOutput(inputText));
  }

  private static String formatOutput(String[] inputText) {
    String output = INDENTATION_4 + BREAK_LINE + "\n";
    for (int i = 0; i < inputText.length; i++) {
      output += INDENTATION_5 + inputText[i] + "\n";
    }
    output += INDENTATION_4 + BREAK_LINE + "\n";
    return output;
  }

  private static void print(String[] inputText) {
    System.out.println(formatOutput(inputText));
  }

  public static void main(String[] args) {
    print(GREETING);
    Scanner scanner = new Scanner(System.in);
    while (true) {
      String command = scanner.nextLine();
      if (command.equals(Command.BYE.getCommand())) {
        scanner.close();
        break;
      }
      print(command);
    }
    print(FAREWELL);
  }
}
