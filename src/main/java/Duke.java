import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

  private static final String INDENTATION_1 = " ";
  private static final String INDENTATION_4 = "    ";
  private static final String INDENTATION_5 = "     ";
  private static final String BREAK_LINE = "____________________________________________________________";
  private static final List<String> GREETING = List.of("Hello! I'm Fergus' Chatbot", "What can I do for you?");
  private static final String FAREWELL = "Bye. Hope to see you again soon!";
  private static final String DONE = "Nice! I've marked this task as done:";
  private static final String ALREADY_DONE = "Error! The task has already been marked as complete!";

  private static String formatOutput(String inputText) {
    return INDENTATION_4 + BREAK_LINE + "\n" + INDENTATION_5 + inputText + "\n" + INDENTATION_4 + BREAK_LINE + "\n";
  }

  private static void print(String inputText) {
    System.out.println(formatOutput(inputText));
  }

  private static String formatOutput(List<String> inputText) {
    String output = INDENTATION_4 + BREAK_LINE + "\n";
    for (int i = 0; i < inputText.size(); i++) {
      output += INDENTATION_5 + inputText.get(i) + "\n";
    }
    output += INDENTATION_4 + BREAK_LINE + "\n";
    return output;
  }

  private static void print(List<String> inputText) {
    System.out.println(formatOutput(inputText));
  }

  public static final String BYE_ENUM = "bye";
  public static final String LIST_ENUM = "list";
  public static final String DONE_ENUM = "done";

  public static void handleList(List<Task> taskList) {
    List<String> outputList = new ArrayList<>();
    for (int i = 0; i < taskList.size(); i++) {
      outputList.add(i + 1 + ". " + taskList.get(i));
    }
    print(outputList);
  }

  public static void handleAdd(List<Task> taskList, String newTaskDescription) {
    Task newTask = new Task(newTaskDescription);
    taskList.add(newTask);
    print("added: " + newTask.toString());
  }

  public static void handleDone(List<Task> taskList, int index) {
    Task indexedTask = taskList.get(index - 1);
    if (indexedTask.getDone()) {
      print(ALREADY_DONE);
      return;
    }
    indexedTask.markAsDone();
    print(List.of(DONE, INDENTATION_1 + indexedTask.toString()));
  }

  public static void handleCommands() {
    List<Task> taskArray = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);
    boolean hasCommands = true;

    while (hasCommands) {
      String commandLine = scanner.nextLine();
      String[] commands = commandLine.split(" ");
      String command = commands[0];
      switch (command) {
        case BYE_ENUM:
          {
            scanner.close();
            hasCommands = false;
            break;
          }
        case LIST_ENUM:
          {
            handleList(taskArray);
            break;
          }
        case DONE_ENUM:
          {
            handleDone(taskArray, Integer.parseInt(commands[1]));
            break;
          }
        default:
          {
            handleAdd(taskArray, commandLine);
            break;
          }
      }
    }
  }

  public static void main(String[] args) {
    print(GREETING);
    handleCommands();
    print(FAREWELL);
  }
}
