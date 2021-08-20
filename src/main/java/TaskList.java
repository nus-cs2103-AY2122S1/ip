import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TaskList {

  private List<Task> taskArray;

  public TaskList(List<String> taskArrayAsString) {
    this.taskArray = convertStringsToTasks(taskArrayAsString);
  }

  private static final String ERROR_UNKNOWN_FILE_COMMAND = "☹ OOPS!!! The saved file might be corrupted!";
  private static final String LIST = "Here are the tasks in your list:";
  private static final String SAVE = "Saved the file successfully! The following is saved:\n";
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
        case Duke.EVENT_ENUM:
          {
            String date = commands[3];
            newTaskArray.add(new Event(description, date, done));
            break;
          }
        case Duke.DEADLINE_ENUM:
          {
            String date = commands[3];
            newTaskArray.add(new Deadline(description, date, done));
            break;
          }
        case Duke.TODO_ENUM:
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
    List<String> outputList = new ArrayList<>();
    outputList.add(LIST);
    for (int i = 0; i < this.taskArray.size(); i++) {
      outputList.add(i + 1 + ". " + this.taskArray.get(i));
    }
    Printer.print(outputList);
  }

  public void handleAddHelper(Task newTask) {
    this.taskArray.add(newTask);
    Printer.print(Printer.addTaskString(newTask.toString(), Integer.toString(this.taskArray.size())));
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
    Task indexedTask = this.taskArray.get(taskIndex - 1);
    String output = indexedTask.handleMarkAsDone();
    Printer.print(output);
  }

  public void handleDelete(int taskIndex) {
    if (taskIndex < -1 || taskIndex >= this.taskArray.size()) Printer.print(ERROR_DELETE_INVALID_INDEX);
    Task deletedTask = this.taskArray.remove(taskIndex - 1);
    Printer.print(Printer.deleteTaskString(deletedTask.toString(), Integer.toString(this.taskArray.size())));
  }

  public void handleSave(Storage storage) {
    try {
      String fileContents = storage.writeToFile(this.taskArray);
      List<String> fileContentStrings = Arrays.asList(fileContents.split("\n"));
      List<String> output = new ArrayList<>();
      output.add(SAVE);
      output.addAll(fileContentStrings);
      Printer.print(output);
    } catch (IOException e) {
      Printer.print(ERROR_SAVE);
    }
  }
}
