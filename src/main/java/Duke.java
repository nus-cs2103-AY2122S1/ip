import java.util.Scanner;

public class Duke {
  public static void main(String[] args) {
    // Greet
    String greeting = "    ____________________________________________________________\n"
        + "     Hello! I'm Duke\n"
        + "     What can I do for you?\n"
        + "    ____________________________________________________________\n";
    System.out.println(greeting);

    // Create scanner
    Scanner scanner = new Scanner(System.in);

    // Initialize command
    String command = "";

    // Exit command
    String exitCommand = "bye";
    String exitResponse = "    ____________________________________________________________\n"
        + "     Bye. Hope to see you again soon!\n"
        + "    ____________________________________________________________\n";

    // Duke chatbot
    while (!command.equals(exitCommand)) {
      command = scanner.nextLine();
      String response = "    ____________________________________________________________\n"
          + "     " + command + "\n"
          + "    ____________________________________________________________\n";
      System.out.println(response);
    }

    // Exit
    System.out.println(exitResponse);
  }
}
