package duke;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

abstract class Printer {

  private Printer() {
    System.out.println("Don't try to initialize this utility class! :(");
  }

  private static final String INDENTATION_1 = " ";
  private static final String INDENTATION_4 = "    ";
  private static final String INDENTATION_5 = "     ";
  private static final String BREAK_LINE = "____________________________________________________________";

  /**
   * Returns the proper format for adding task string.
   *
   * @param task The formatted string of a task.
   * @param totalTasks The total number of tasks.
   * @return
   */
  public static List<String> addTaskString(String task, String totalTasks) {
    return List.of(
      "Got it. I've added this task: ",
      INDENTATION_1 + task,
      String.format("Now you have %s tasks in the list.", totalTasks)
    );
  }

  /**
   * Returns the proper format for deleting task string.
   *
   * @param task The formatted string of a task.
   * @param totalTasks The total number of tasks.
   * @return
   */
  public static List<String> deleteTaskString(String task, String totalTasks) {
    return List.of(
      "Noted. I've removed this task: ",
      INDENTATION_1 + task,
      String.format("Now you have %s tasks in the list.", totalTasks)
    );
  }

  /**
   * Parses the commands for task name from user-input.
   *
   * @param task The formatted string of a task.
   * @param totalTasks The total number of tasks.
   * @return
   */
  public static String getTaskName(String[] commands) {
    int indexOfDateSeparator = IntStream
      .range(0, commands.length)
      .filter(i -> commands[i].equals("/at") || commands[i].equals("/by"))
      .findFirst()
      .orElse(commands.length);
    String[] taskNames = Arrays.copyOfRange(commands, 1, indexOfDateSeparator);
    return Arrays.stream(taskNames).collect(Collectors.joining(" "));
  }

  /**
   * Parses the commands for task date from user-input.
   *
   * @param task The formatted string of a task.
   * @param totalTasks The total number of tasks.
   * @return
   */
  public static String getTaskDate(String[] commands) {
    int indexOfDateSeparator = IntStream
      .range(0, commands.length)
      .filter(i -> commands[i].equals("/at") || commands[i].equals("/by"))
      .findFirst()
      .orElse(commands.length);
    String[] taskNames = Arrays.copyOfRange(commands, indexOfDateSeparator + 1, commands.length);
    return Arrays.stream(taskNames).collect(Collectors.joining(" "));
  }

  //TODO: Make both sets of formatOutput and print to handle String ... args instead
  public static String formatOutput(String inputText) {
    return INDENTATION_4 + BREAK_LINE + "\n" + INDENTATION_5 + inputText + "\n" + INDENTATION_4 + BREAK_LINE + "\n";
  }

  public static void print(String inputText) {
    System.out.println(formatOutput(inputText));
  }

  /**
   * Formats by surrounding each text of the list of texts with 2 breaklines
   *
   * @param inputText
   * @return
   */
  public static String formatOutput(List<String> inputText) {
    String output = INDENTATION_4 + BREAK_LINE + "\n";
    for (int i = 0; i < inputText.size(); i++) {
      output += INDENTATION_5 + inputText.get(i) + "\n";
    }
    output += INDENTATION_4 + BREAK_LINE + "\n";
    return output;
  }

  public static void print(List<String> inputText) {
    System.out.println(formatOutput(inputText));
  }
}
