import java.util.ArrayList;
import java.util.function.Function;
import tasks.InvalidTaskException;
import tasks.Task;

/**
 * Handles matching input to behaviour and execution action.
 * Using function `takeInput` with the input string will run the function
 * and return true or false (whether or not to continue monitoring input)
 */
public class DukeLogic {

  private static ArrayList<Task> tasks = new ArrayList<>();

  enum Actions {
    DELETE,
    MARK_COMPLETE,
    //  EDIT
    //  MARK_INCOMPLETE,
  }

  /**
   * Takes in and handles input for Duke (the logic).
   *
   * @param input User input passed in to Duke.
   * @return boolean of whether or not to continue Duke.
   */
  public static boolean takeInput(String input) {
    if (matches("bye").apply(input)) {
      Duke.renderOutput("Goodbye!");
      return false;
    } else if (matches("").apply(input)) {
      return true;
    } else if (matches("list").apply(input)) {
      listTasks();
      return true;
    } else if (startsWithOrEquals("done ").apply(input)) {
      doTaskAction(getArgs(input, "done "), Actions.MARK_COMPLETE);
      return true;
    } else if (startsWithOrEquals("delete ").apply(input)) {
      doTaskAction(getArgs(input, "delete "), Actions.DELETE);
      return true;
    } else if (startsWithOrEquals("todo ").apply(input)) {
      addTask(getArgs(input, "todo "), Task.Type.TODO);
      return true;
    } else if (startsWithOrEquals("deadline ").apply(input)) {
      addTask(getArgs(input, "deadline "), Task.Type.DEADLINE);
      return true;
    } else if (startsWithOrEquals("event ").apply(input)) {
      addTask(getArgs(input, "event "), Task.Type.EVENT);
      return true;
    } else {
      Duke.renderOutput("I did not understand, sorry!");
      return true;
    }
  }

  private static Function<String, Boolean> matches(String phrase) {
    return x -> x.trim().equalsIgnoreCase(phrase);
  }

  private static Function<String, Boolean> startsWithOrEquals(String phrase) {
    return x -> x.trim().startsWith(phrase) || x.trim().equalsIgnoreCase(phrase.trim());
  }

  private static Function<String, Boolean> contains(String phrase) {
    return x -> x.trim().contains(phrase);
  }

  private static String getArgs(String input, String command) {
    return input.substring(input.toLowerCase().indexOf(command) + command.length()).trim();
  }

  private static void listTasks() {
    int taskCount = 1;
    StringBuilder result = new StringBuilder();
    for (Task task : tasks) {
      result.append(String.format("%2d. %s\n", taskCount++, task));
    }
    Duke.renderOutput(result.toString());
  }

  private static void addTask(String taskName, Task.Type type) {
    try {
      Task task = Task.createTask(taskName.trim(), type);
      tasks.add(task);
      Duke.renderOutput("added: " + task);
    } catch (InvalidTaskException err) {
      Duke.renderOutput(err.getMessage());
    }
  }

  private static void doTaskAction(String taskNumStr, Actions action) {
    try {
      int taskNum = Integer.parseInt(taskNumStr);
      Task task = tasks.get(taskNum - 1);
      String output = "";
      switch (action) {
        case DELETE:
          tasks.remove(taskNum - 1);
          output = "Noted. I have deleted the following:\n    " + task;
          output += String.format("%s\nYou now have %d tasks in the list", output, tasks.size());
          break;
        case MARK_COMPLETE:
          task.markComplete(true);
          output = "Great! I've marked this task as done:\n    " + task;
          break;
        default:
          throw new IllegalArgumentException("Invalid action");
      }
      Duke.renderOutput(output);
    } catch (NumberFormatException err) {
      Duke.renderOutput(
        "Which task are you interacting with?\n" +
        "USAGE:\n{action} {task number}\n" +
        "Example: > done 4\n" +
        "         > delete 2\n" +
        "Try the `list` command to see the list of tasks"
      );
    } catch (IndexOutOfBoundsException err) {
      Duke.renderOutput("There is no task at that index.");
    }
  }
}
