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

    // Initialize command variables
    String commandLine = "";
    String command = "";
    int doneTask = -1;

    // Exit command
    final String exitCommand = "bye";
    String exitResponse = "    ____________________________________________________________\n"
        + "     Bye. Hope to see you again soon!\n"
        + "    ____________________________________________________________\n";

    // List command
    final String listCommand = "list";
    Task[] list = new Task[100];
    int index = 0;

    // Done command
    final String doneCommand = "done";

    // Duke chatbot
    while (!command.equals(exitCommand)) {
      // Initialize response content
      String responseContent = "";

      // Draft response content based on command
      switch (command) {
        case listCommand:
          responseContent = "Here are the tasks in your list:\n";
          for (int i = 0; i < index; i++) {
            if (i != index - 1) {
              responseContent = responseContent + "     " + (i + 1) + "." + list[i] + "\n";
            } else {
              responseContent = responseContent + "     " + (i + 1) + "." + list[i];
            }
          }
          break;
        case doneCommand:
          list[doneTask].markAsDone();
          responseContent = "Nice! I've marked this task as done:\n" +
              "       " + list[doneTask];
          break;
        case "":
          responseContent = "";
          break;
        default:
          list[index] = new Task(command);
          index++;
          responseContent = "added: " + command;
          break;
      }
//      if (command.equals(listCommand)) {
//        for (int i = 0; i < index; i++) {
//          if (i != index - 1) {
//            responseContent = responseContent + (i + 1) + "." + list[i] + "\n     ";
//          } else {
//            responseContent = responseContent + (i + 1) + "." + list[i];
//          }
//        }
//      } else if (command.equals("")) {
//        responseContent = "";
//      } else {
//        responseContent = "added: " + command;
//        list[index] = new Task(command);
//        index++;
//      }

      // Response
      String response = "    ____________________________________________________________\n"
          + "     " + responseContent + "\n"
          + "    ____________________________________________________________\n";
      if (!responseContent.equals("")) {
        System.out.println(response);
      }

      // Get command
      commandLine = scanner.nextLine();
      command = commandLine.split(" ")[0];
      if (command.equals(doneCommand)) {
        doneTask = Integer.parseInt(commandLine.split(" ")[1]) - 1;
      } else {
        command = commandLine;
      }
    }

    // Exit
    System.out.println(exitResponse);
  }
}
