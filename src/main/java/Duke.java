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

    // List command
    final String listCommand = "list";
    String[] list = new String[100];
    int index = 0;

    // Duke chatbot
    while (!command.equals(exitCommand)) {
      // Initialize response content
      String responseContent = "";

      // List command
      if (command.equals(listCommand)) {
        for (int i = 0; i < index; i++) {
          if (i != index - 1) {
            responseContent = responseContent + (i + 1) + ". " + list[i] + "\n     ";
          } else {
            responseContent = responseContent + (i + 1) + ". " + list[i];
          }
        }
      } else if (command.equals("")) {
        responseContent = "";
      } else {
        responseContent = "added: " + command;
        list[index] = command;
        index++;
      }

      // Response
      String response = "    ____________________________________________________________\n"
          + "     " + responseContent + "\n"
          + "    ____________________________________________________________\n";
      if (!responseContent.equals("")) {
        System.out.println(response);
      }

      // Get command
      command = scanner.nextLine();
    }

    // Exit
    System.out.println(exitResponse);
  }
}
