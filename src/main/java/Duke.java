import java.util.ArrayList;
import java.util.List;
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
    String responseContent = "";

    // Exit command
    final String exitCommand = "bye";
    String exitResponse = "    ____________________________________________________________\n"
        + "     Bye. Hope to see you again soon!\n"
        + "    ____________________________________________________________\n";

    // List command
    final String listCommand = "list";
    List<Task> list = new ArrayList<>();

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
      if (responseContent.equals("")) {
        // Draft response content based on command
        switch (command) {
          case listCommand:
            if (list.size() == 0) {
              responseContent = "You do not have any tasks.";
            } else {
              responseContent = "Here are the tasks in your list:\n";
              for (int i = 0; i < list.size(); i++) {
                if (i != list.size() - 1) {
                  responseContent = responseContent + "     " + (i + 1) + "." + list.get(i) + "\n";
                } else {
                  responseContent = responseContent + "     " + (i + 1) + "." + list.get(i);
                }
              }
            }
            break;
          case doneCommand:
            list.get(doneTask).markAsDone();
            responseContent = "Nice! I've marked this task as done:\n" +
                "       " + list.get(doneTask);
            break;
          case todoCommand:
            Todo newTodo = new Todo(description);
            list.add(newTodo);
            responseContent = "Got it. I've added this task:\n"
                + "       " + newTodo + "\n"
                + "     Now you have " + list.size() + (list.size() > 1 ? " tasks" : " task") + " in the list.";
            break;
          case deadlineCommand:
            Deadline newDeadline = new Deadline(description, by);
            list.add(newDeadline);
            responseContent = "Got it. I've added this task:\n"
                + "       " + newDeadline + "\n"
                + "     Now you have " + list.size() + (list.size() > 1 ? " tasks" : " task") + " in the list.";
            break;
          case eventCommand:
            Event newEvent = new Event(description, at);
            list.add(newEvent);
            responseContent = "Got it. I've added this task:\n"
                + "       " + newEvent + "\n"
                + "     Now you have " + list.size() + (list.size() > 1 ? " tasks" : " task") + " in the list.";
            break;
          default:
            break;
        }
      }

      // Response
      if (!responseContent.equals("")) {
        String response = "    ____________________________________________________________\n"
            + "     " + responseContent + "\n"
            + "    ____________________________________________________________\n";
        System.out.println(response);
      }

      // Extract command variables
      commandLine = scanner.nextLine();
      command = commandLine.split(" ")[0];
      String rest;

      try {
        switch (command) {
          case doneCommand:
            if (commandLine.split(" ").length == 1) {
              throw new MissingArgumentException("Done", "task");
            } else {
              rest = commandLine.split(" ")[1];
              doneTask = Integer.parseInt(rest) - 1;
              if (doneTask < 0) {
                throw new InvalidArgumentException(doneTask);
              } else if (doneTask >= list.size()) {
                throw new InvalidArgumentException(list.size());
              }
            }
            break;
          case deadlineCommand:
            if (commandLine.split(" ").length == 1) {
              throw new EmptyDescriptionException("Deadline");
            } else {
              rest = commandLine.split(" ", 2)[1];
              if (rest.split(" /by ").length == 1) {
                throw new MissingArgumentException("Deadline", "/by");
              } else {
                description = rest.split(" /by ")[0];
                by = rest.split(" /by ")[1];
              }
            }
            break;
          case eventCommand:
            if (commandLine.split(" ").length == 1) {
              throw new EmptyDescriptionException("Event");
            } else {
              rest = commandLine.split(" ", 2)[1];
              if (rest.split(" /at ").length == 1) {
                throw new MissingArgumentException("Event", "/at");
              } else {
                description = rest.split(" /at ")[0];
                at = rest.split(" /at ")[1];
              }
            }
            break;
          case todoCommand:
            if (commandLine.split(" ").length == 1) {
              throw new EmptyDescriptionException("Todo");
            } else {
              rest = commandLine.split(" ", 2)[1];
              description = rest;
            }
            break;
          case "":
          case listCommand:
            break;
          default:
            throw new UnknownCommandException();
        }
        responseContent = "";
      } catch (DukeException error) {
        responseContent = error.toString();
      }
    }

    // Exit
    System.out.println(exitResponse);
    scanner.close();
  }
}
