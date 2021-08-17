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
    String commandLine;
    String command = "";
    String description = "";

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
    int doneTask = -1;

    // Todo command
    final String todoCommand = "todo";

    // Deadline command
    final String deadlineCommand = "deadline";
    String by = "";

    // Event command
    final String eventCommand = "event";
    String at = "";

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
          if (doneTask > -1) {
            list[doneTask].markAsDone();
          }
          responseContent = "Nice! I've marked this task as done:\n" +
              "       " + list[doneTask];
          break;
        case todoCommand:
          list[index] = new Todo(description);
          index++;
          if (index == 1) {
            responseContent = "Got it. I've added this task:\n"
                + "       " + list[index - 1] + "\n"
                + "     Now you have " + index + " task in the list.";
          } else {
            responseContent = "Got it. I've added this task:\n"
                + "       " + list[index - 1] + "\n"
                + "     Now you have " + index + " tasks in the list.";
          }
          break;
        case deadlineCommand:
          list[index] = new Deadline(description, by);
          index++;
          if (index == 1) {
            responseContent = "Got it. I've added this task:\n"
                + "       " + list[index - 1] + "\n"
                + "     Now you have " + index + " task in the list.";
          } else {
            responseContent = "Got it. I've added this task:\n"
                + "       " + list[index - 1] + "\n"
                + "     Now you have " + index + " tasks in the list.";
          }
          break;
        case eventCommand:
          list[index] = new Event(description, at);
          index++;
          if (index == 1) {
            responseContent = "Got it. I've added this task:\n"
                + "       " + list[index - 1] + "\n"
                + "     Now you have " + index + " task in the list.";
          } else {
            responseContent = "Got it. I've added this task:\n"
                + "       " + list[index - 1] + "\n"
                + "     Now you have " + index + " tasks in the list.";
          }
          break;
        default:
          break;
      }

      // Response
      String response = "    ____________________________________________________________\n"
          + "     " + responseContent + "\n"
          + "    ____________________________________________________________\n";
      if (!responseContent.equals("")) {
        System.out.println(response);
      }

      // Extract command variables
      commandLine = scanner.nextLine();
      command = commandLine.split(" ")[0];
      String rest;

      switch (command) {
        case doneCommand:
          rest = commandLine.split(" ", 2)[1];
          doneTask = Integer.parseInt(rest) - 1;
          break;
        case deadlineCommand:
          rest = commandLine.split(" ", 2)[1];
          description = rest.split(" /by ")[0];
          by = rest.split(" /by ")[1];
          break;
        case eventCommand:
          rest = commandLine.split(" ", 2)[1];
          description = rest.split(" /at ")[0];
          at = rest.split(" /at ")[1];
          break;
        case todoCommand:
          rest = commandLine.split(" ", 2)[1];
          description = rest;
          break;
        default:
          break;
      }
    }

    // Exit
    System.out.println(exitResponse);
    scanner.close();
  }
}
