package duke;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class TaskList {

  private List<Task> taskArray;

  public TaskList(List<String> taskArrayAsString) {
    this.taskArray = convertStringsToTasks(taskArrayAsString);
  }

  private static final String MESSAGE_LIST = "Here are the tasks in your list:";
  private static final String MESSAGE_LIST_EMPTY = "There are no tasks in your list! ☹";
  private static final String MESSAGE_SAVE = "Saved the file successfully! The following is saved:\n";
  private static final String MESSAGE_RESET = "All your tasks have been reset!";
  private static final String MESSAGE_FIND = "I found your tasks!";
  private static final String MESSAGE_FIND_EMPTY = "No tasks were found with this description! ☹";
  private static final String MESSAGE_FIND_LIST_EMPTY = "There are no tasks in your list to find! ☹";
  private static final String ERROR_UNKNOWN_FILE_COMMAND = "☹ OOPS!!! The saved file might be corrupted!";
  private static final String ERROR_TODO_MISSING_DESCRIPTION = "☹ OOPS!!! The description of a todo cannot be empty.";
  private static final String ERROR_DELETE_INVALID_INDEX = "☹ OOPS!!! Please state a valid index to delete!";
  private static final String ERROR_SAVE = "☹ OOPS!!! I am unable to save the file for an unknown reason!";

  private List<Task> convertStringsToTasks(List<String> taskArrayAsString) {
    List<Task> newTaskArray = new ArrayList<>();

    if (taskArrayAsString.isEmpty()) {
      return newTaskArray;
    }

    for (String taskAsString : taskArrayAsString) {
      String[] commands = taskAsString.split(" \\| ");
      String command = commands[0];
      boolean done = commands[1].equals("1");
      String description = commands[2];
      switch (command) {
        case Duke.COMMAND_EVENT:
          {
            String date = commands[3];
            newTaskArray.add(new Event(description, date, done));
            break;
          }
        case Duke.COMMAND_DEADLINE:
          {
            String date = commands[3];
            newTaskArray.add(new Deadline(description, date, done));
            break;
          }
        case Duke.COMMAND_TODO:
          {
            newTaskArray.add(new Todo(description, done));
            break;
          }
        default:
          {
            Printer.print(ERROR_UNKNOWN_FILE_COMMAND);
          }
      }
    }
    return newTaskArray;
  }

  public void handleList() {
    if (taskArray.isEmpty()) {
      Printer.print(MESSAGE_LIST_EMPTY);
      return;
    }

    Printer.printNumberedList(MESSAGE_LIST, taskArray);
  }

  public void handleAddHelper(Task newTask) {
    taskArray.add(newTask);
    Printer.print(Printer.addTaskString(newTask.toString(), Integer.toString(taskArray.size())));
  }

  public void handleAddToDo(String[] command) {
    String newTaskDescription = Printer.getTaskName(command);
    if (newTaskDescription.equals("")) {
      Printer.print(ERROR_TODO_MISSING_DESCRIPTION);
      return;
    }
    Todo newTask = new Todo(newTaskDescription);
    handleAddHelper(newTask);
  }

  public void handleAddDeadline(String[] command) {
    String newTaskDescription = Printer.getTaskName(command);
    String newTaskDate = Printer.getTaskDate(command);
    Deadline newTask = new Deadline(newTaskDescription, newTaskDate);
    handleAddHelper(newTask);
  }

  public void handleAddEvent(String[] command) {
    String newTaskDescription = Printer.getTaskName(command);
    String newTaskDate = Printer.getTaskDate(command);
    Event newTask = new Event(newTaskDescription, newTaskDate);
    handleAddHelper(newTask);
  }

  public void handleDone(int taskIndex) {
    Task indexedTask = taskArray.get(taskIndex - 1);
    String output = indexedTask.handleMarkAsDone();
    Printer.print(output);
  }

  public void handleDelete(int taskIndex) {
    if (taskIndex < -1 || taskIndex >= taskArray.size()) Printer.print(ERROR_DELETE_INVALID_INDEX);
    Task deletedTask = taskArray.remove(taskIndex - 1);
    Printer.print(Printer.deleteTaskString(deletedTask.toString(), Integer.toString(taskArray.size())));
  }

  public void handleSave(Storage storage) {
    try {
      String fileContents = storage.writeToFile(taskArray);
      List<String> fileContentStrings = Arrays.asList(fileContents.split("\n"));
      List<String> output = new ArrayList<>();
      output.add(MESSAGE_SAVE);
      output.addAll(fileContentStrings);
      Printer.print(output);
    } catch (IOException e) {
      Printer.print(ERROR_SAVE);
    }
  }

  public void handleFind(String description) {
    if (taskArray.isEmpty()) {
      Printer.print(MESSAGE_FIND_LIST_EMPTY);
      return;
    }

    Pattern pattern = Pattern.compile(description, Pattern.CASE_INSENSITIVE);
    List<Task> tasksFound = taskArray
      .stream()
      .filter(
        x -> {
          Matcher matcher = pattern.matcher(x.getDescription());
          return matcher.find();
        }
      )
      .collect(Collectors.toList());

    if (tasksFound.isEmpty()) {
      Printer.print(MESSAGE_FIND_EMPTY);
      return;
    }

    Printer.printNumberedList(MESSAGE_FIND, tasksFound);
  }

  public void handleReset() {
    taskArray = new ArrayList<>();
    Printer.print(MESSAGE_RESET);
  }
}
