import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Duke {

  private static final String INDENTATION_1 = " ";
  private static final String INDENTATION_4 = "    ";
  private static final String INDENTATION_5 = "     ";
  private static final String BREAK_LINE = "____________________________________________________________";
  private static final List<String> GREETING = List.of("Hello! I'm Fergus' Chatbot", "What can I do for you?");
  private static final String FAREWELL = "Bye. Hope to see you again soon!";
  private static final String LIST = "Here are the tasks in your list:";
  private static final String ERROR_TODO_MISSING_DESCRIPTION = "☹ OOPS!!! The description of a todo cannot be empty.";
  private static final String ERROR_UNKNOWN_COMMAND = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";

  private static List<String> addTaskString(String task, String totalTasks) {
    return List.of(
      "Got it. I've added this task: ",
      INDENTATION_1 + task,
      String.format("Now you have %s tasks in the list.", totalTasks)
    );
  }

  private static String getTaskName(String[] commands) {
    int indexOfDateSeparator = IntStream
      .range(0, commands.length)
      .filter(i -> commands[i].equals("/at") || commands[i].equals("/by"))
      .findFirst()
      .orElse(commands.length);
    String[] taskNames = Arrays.copyOfRange(commands, 1, indexOfDateSeparator);
    return Arrays.stream(taskNames).collect(Collectors.joining(" "));
  }

  private static String getTaskDate(String[] commands) {
    int indexOfDateSeparator = IntStream
      .range(0, commands.length)
      .filter(i -> commands[i].equals("/at") || commands[i].equals("/by"))
      .findFirst()
      .orElse(commands.length);
    String[] taskNames = Arrays.copyOfRange(commands, indexOfDateSeparator + 1, commands.length);
    return Arrays.stream(taskNames).collect(Collectors.joining(" "));
  }

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

  private static final String BYE_ENUM = "bye";
  private static final String LIST_ENUM = "list";
  private static final String DONE_ENUM = "done";
  private static final String TODO_ENUM = "todo";
  private static final String EVENT_ENUM = "event";
  private static final String DEADLINE_ENUM = "deadline";

  public static void handleList(List<Task> taskList) {
    List<String> outputList = new ArrayList<>();
    outputList.add(LIST);
    for (int i = 0; i < taskList.size(); i++) {
      outputList.add(i + 1 + ". " + taskList.get(i));
    }
    print(outputList);
  }

  public static void handleAddToDo(List<Task> taskList, String[] command) {
    String newTaskDescription = getTaskName(command);
    if (newTaskDescription.equals("")) {
      print(ERROR_TODO_MISSING_DESCRIPTION);
      return;
    }
    Todo newTask = new Todo(newTaskDescription);
    handleAddHelper(taskList, newTask);
  }

  public static void handleAddDeadline(List<Task> taskList, String[] command) {
    String newTaskDescription = getTaskName(command);
    String newTaskDate = getTaskDate(command);
    Deadline newTask = new Deadline(newTaskDescription, newTaskDate);
    handleAddHelper(taskList, newTask);
  }

  public static void handleAddEvent(List<Task> taskList, String[] command) {
    String newTaskDescription = getTaskName(command);
    String newTaskDate = getTaskDate(command);
    Event newTask = new Event(newTaskDescription, newTaskDate);
    handleAddHelper(taskList, newTask);
  }

  public static void handleAddHelper(List<Task> taskList, Task newTask) {
    taskList.add(newTask);
    print(addTaskString(newTask.toString(), Integer.toString(taskList.size())));
  }

  public static void handleDone(List<Task> taskList, int index) {
    Task indexedTask = taskList.get(index - 1);
    String output = indexedTask.handleMarkAsDone();
    print(output);
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
        case EVENT_ENUM:
          {
            handleAddEvent(taskArray, commands);
            break;
          }
        case DEADLINE_ENUM:
          {
            handleAddDeadline(taskArray, commands);
            break;
          }
        case TODO_ENUM:
          {
            handleAddToDo(taskArray, commands);
            break;
          }
        case DONE_ENUM:
          {
            handleDone(taskArray, Integer.parseInt(commands[1]));
            break;
          }
        default:
          {
            print(ERROR_UNKNOWN_COMMAND);
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
