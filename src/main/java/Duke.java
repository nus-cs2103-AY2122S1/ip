import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Duke {

  private static final List<String> GREETING = List.of("Hello! I'm Fergus' Chatbot", "What can I do for you?");
  private static final String FAREWELL = "Bye. Hope to see you again soon!";
  private static final String LIST = "Here are the tasks in your list:";
  private static final String SAVE = "Saved the file successfully! The following is saved:\n";
  private static final String ERROR_TODO_MISSING_DESCRIPTION = "☹ OOPS!!! The description of a todo cannot be empty.";
  private static final String ERROR_UNKNOWN_COMMAND = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
  private static final String ERROR_UNKNOWN_FILE_COMMAND = "☹ OOPS!!! The saved file might be corrupted!";
  private static final String ERROR_DELETE_INVALID_INDEX = "☹ OOPS!!! Please state a valid index to delete!";
  private static final String ERROR_SAVE = "☹ OOPS!!! I am unable to save the file for an unknown reason!";

  private static final String BYE_ENUM = "bye";
  private static final String LIST_ENUM = "list";
  private static final String DONE_ENUM = "done";
  protected static final String TODO_ENUM = "todo";
  protected static final String EVENT_ENUM = "event";
  protected static final String DEADLINE_ENUM = "deadline";
  private static final String DELETE_ENUM = "delete";
  private static final String SAVE_ENUM = "save";

  public static void handleList(List<Task> taskList) {
    List<String> outputList = new ArrayList<>();
    outputList.add(LIST);
    for (int i = 0; i < taskList.size(); i++) {
      outputList.add(i + 1 + ". " + taskList.get(i));
    }
    Printer.print(outputList);
  }

  public static void handleAddHelper(List<Task> taskList, Task newTask) {
    taskList.add(newTask);
    Printer.print(Printer.addTaskString(newTask.toString(), Integer.toString(taskList.size())));
  }

  public static void handleAddToDo(List<Task> taskList, String[] command) {
    String newTaskDescription = Printer.getTaskName(command);
    if (newTaskDescription.equals("")) {
      Printer.print(ERROR_TODO_MISSING_DESCRIPTION);
      return;
    }
    Todo newTask = new Todo(newTaskDescription);
    handleAddHelper(taskList, newTask);
  }

  public static void handleAddDeadline(List<Task> taskList, String[] command) {
    String newTaskDescription = Printer.getTaskName(command);
    String newTaskDate = Printer.getTaskDate(command);
    Deadline newTask = new Deadline(newTaskDescription, newTaskDate);
    handleAddHelper(taskList, newTask);
  }

  public static void handleAddEvent(List<Task> taskList, String[] command) {
    String newTaskDescription = Printer.getTaskName(command);
    String newTaskDate = Printer.getTaskDate(command);
    Event newTask = new Event(newTaskDescription, newTaskDate);
    handleAddHelper(taskList, newTask);
  }

  public static void handleDone(List<Task> taskList, int index) {
    Task indexedTask = taskList.get(index - 1);
    String output = indexedTask.handleMarkAsDone();
    Printer.print(output);
  }

  public static void handleDelete(List<Task> taskList, int index) {
    if (index < -1 || index >= taskList.size()) Printer.print(ERROR_DELETE_INVALID_INDEX);
    Task deletedTask = taskList.remove(index - 1);
    Printer.print(Printer.deleteTaskString(deletedTask.toString(), Integer.toString(taskList.size())));
  }

  public static void handleSave(List<Task> taskList) {
    try {
      String fileContents = Writer.writeToFile(taskList);
      List<String> fileContentStrings = Arrays.asList(fileContents.split("\n"));
      List<String> output = new ArrayList<>();
      output.add(SAVE);
      output.addAll(fileContentStrings);
      Printer.print(output);
    } catch (IOException e) {
      Printer.print(ERROR_SAVE);
    }
  }

  public static List<Task> handleLoadSavedFile() {
    List<String> taskArrayAsString = Reader.readTasksFromFile();
    List<Task> taskArray = new ArrayList<>();

    if (taskArrayAsString.isEmpty()) {
      return taskArray;
    }

    for (String taskAsString : taskArrayAsString) {
      String[] commands = taskAsString.split(" \\| ");
      String command = commands[0];
      boolean done = commands[1].equals("1");
      String description = commands[2];
      switch (command) {
        case EVENT_ENUM:
          {
            String date = commands[3];
            taskArray.add(new Event(description, date, done));
            break;
          }
        case DEADLINE_ENUM:
          {
            String date = commands[3];
            taskArray.add(new Deadline(description, date, done));
            break;
          }
        case TODO_ENUM:
          {
            taskArray.add(new Todo(description, done));
            break;
          }
        default:
          {
            Printer.print(ERROR_UNKNOWN_FILE_COMMAND);
          }
      }
    }
    return taskArray;
  }

  public static void handleCommands(List<Task> taskArray) {
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
        case DELETE_ENUM:
          {
            handleDelete(taskArray, Integer.parseInt(commands[1]));
            break;
          }
        case SAVE_ENUM:
          {
            handleSave(taskArray);
            break;
          }
        default:
          {
            Printer.print(ERROR_UNKNOWN_COMMAND);
          }
      }
    }
  }

  public static void main(String[] args) {
    Printer.print(GREETING);
    List<Task> taskArray = handleLoadSavedFile();
    handleCommands(taskArray);
    Printer.print(FAREWELL);
  }
}
