import java.util.Scanner;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class Duke {
  public static void main(String[] args) {
    /*
       String logo = " ____        _        \n"
       + "|  _ \\ _   _| | _____ \n"
       + "| | | | | | | |/ / _ \\\n"
       + "| |_| | |_| |   <  __/\n"
       + "|____/ \\__,_|_|\\_\\___|\n";
       System.out.println("Hello from\n" + logo);
       */
    ArrayList<Task> userText = new ArrayList<>();
    String DONE_REGEX = "done [0-9]+";
    String TODO_REGEX = "todo [\\w\\s-]*";
    String DEADLINE_REGEX = "deadline [\\w\\s-]* \\/by [\\w\\s-]*";
    String EVENT_REGEX = "event [\\w\\s-]* \\/at [\\w\\s-]*";
    String LINE = "    --------------------------------------------------";
    String INDENTATION = "      ";
    Scanner scan = new Scanner(System.in);
    // abstract out printing of response
    System.out.println(LINE + "\n" + INDENTATION + "Hello! I'm Duke\n" + INDENTATION + "What can I do for you?\n" + LINE);

    while (true) {
      String command = scan.nextLine();

      try {
        if (command.equals("list")) {
          System.out.println(LINE);
          System.out.println(INDENTATION + "Here are the tasks in your list:");
          for (int i = 0; i < userText.size(); i++) {
            int tempNum = i + 1;
            System.out.println(INDENTATION + tempNum  + ". " + userText.get(i));
          }
          System.out.println(LINE);
        } else if (command.equals("bye")) {
          System.out.println(LINE + "\n" + INDENTATION + "Bye. Hope to see you again soon!\n" + LINE);
          break;
        } else if (Pattern.matches(DONE_REGEX, command)) {
          String indexStr = command.substring(5);
          int index = Integer.parseInt(indexStr) - 1;
          userText.get(index).markAsDone();
          System.out.println(LINE + "\n" + INDENTATION + "Nice! I've marked this task as done:\n" + INDENTATION + "  " + userText.get(index) + "\n"  + LINE);
        } else if (command.equals("todo")) { 
          throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
        } else if (Pattern.matches(TODO_REGEX, command)) {
          String name = command.substring(5);
          userText.add(new ToDo(name));
          System.out.println(LINE + "\n" + INDENTATION + "Got it. I've added this task:\n" + INDENTATION + "  " + userText.get(userText.size()-1) + "\n" + INDENTATION + "Now you have " + userText.size() + " tasks in the list.\n" + LINE);
        } else if (Pattern.matches(DEADLINE_REGEX, command)) { 
          int breakPos = command.indexOf("/by");
          String name = command.substring(9, breakPos - 1);
          String due = command.substring(breakPos + 4);
          userText.add(new Deadline(name, due)); 
          System.out.println(LINE + "\n" + INDENTATION + "Got it. I've added this task:\n" + INDENTATION + "  " + userText.get(userText.size()-1) + "\n" + INDENTATION + "Now you have " + userText.size() + " tasks in the list.\n" + LINE);
        } else if (Pattern.matches(EVENT_REGEX, command)) { 
          int breakPos = command.indexOf("/at");
          String name = command.substring(6, breakPos - 1);
          String time = command.substring(breakPos + 4);
          userText.add(new Event(name, time));
          System.out.println(LINE + "\n" + INDENTATION + "Got it. I've added this task:\n" + INDENTATION + "  " + userText.get(userText.size()-1) + "\n" + INDENTATION + "Now you have " + userText.size() + " tasks in the list.\n" + LINE);
        } else {
          throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means ):");
        }
      }
      catch (DukeException e) {
         System.out.println(LINE + "\n" + INDENTATION + e.getMessage() + "\n" + LINE);
      }
    }
  }
}
