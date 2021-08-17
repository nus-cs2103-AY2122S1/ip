import java.util.ArrayList;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * Handles matching input to behaviour and execution action.
 * Using function `takeInput` with the input string will run the function
 * and return true or false (whether or not to continue monitoring input)
 */
public class DukeLogic {

  private static ArrayList<Task> tasks = new ArrayList<>();

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
      Duke.renderOutput(listTasks());
      return true;
    } else {
      addTask(input.trim());
      Duke.renderOutput("added: " + input.trim());
      return true;
    }
  }

  private static Function<String, Boolean> matches(String phrase) {
    return x -> x.trim().equalsIgnoreCase(phrase);
  }

  private static Function<String, Boolean> contains(String phrase) {
    return x -> x.trim().contains(phrase);
  }

  private static String listTasks() {
    int taskCount = 1;
    StringBuilder result = new StringBuilder();
    for (Task task : tasks) {
      result.append(String.format("%d %s\n", taskCount++, task));
    }
    return result.toString();
  }

  private static void addTask(String taskName) {
    tasks.add(new Task(taskName));
  }
}
