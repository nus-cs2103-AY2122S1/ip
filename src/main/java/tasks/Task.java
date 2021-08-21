package tasks;

import java.util.regex.Pattern;

/**
 * Task class that encapsulate task behaviour and data.
 */
public class Task {

  /**
   * TODO: task without a date
   * EVENT: task with a start date and end date
   * DEADLINE: task with a single date associated with it.
   */
  public enum Type {
    TODO,
    EVENT,
    DEADLINE,
  }

  private static final String DELIMITER = "--|--";
  private final String title;
  private final Type type;
  private boolean isComplete = false;

  /**
   * Static factory method for creating Tasks.
   *
   * @param inputString Includes title and date string (if applicable).
   * @param type Task Type (enum)
   * @return instance of a subclass of Task.
   */
  public static Task createTask(String inputString, Type type) {
    inputString = inputString.trim();
    String[] args;
    switch (type) {
      case TODO:
        return new TodoTask(inputString);
      case EVENT:
        args = inputString.split(" /at ");
        if (args.length != 2) {
          throw new InvalidTaskException("Expected '{title} /at {date}' for event tasks");
        }
        return new EventTask(args[0], args[1]);
      case DEADLINE:
        args = inputString.split(" /by ");
        if (args.length != 2) {
          throw new InvalidTaskException("Expected '{title} /by {dates}' for deadline tasks");
        }
        return new DeadlineTask(args[0], args[1]);
      default:
        throw new InvalidTaskException("Task type not expected.");
    }
  }

  protected Task(String title, Type type) {
    title = title.trim();
    if (title.length() == 0) {
      throw new InvalidTaskException("Task description cannot be empty");
    }
    this.title = title;
    this.type = type;
  }

  public static Task stringToTask(String stringifiedTask) {
    // {TYPE}|{DESCRIPTION}|{DATE or DATES or BLANK}
    String[] taskAttr = stringifiedTask.split(Pattern.quote(DELIMITER));
    if (taskAttr.length < 2 || taskAttr.length > 3) {
      throw new IllegalArgumentException(
        "This task is not correctly stringified. - " + stringifiedTask
      );
    }

    String type = taskAttr[0];
    String descr = taskAttr[1];
    String date = taskAttr.length == 2 ? "" : taskAttr[2];

    switch (type) {
      case "T":
        return Task.createTask(descr, Task.Type.TODO);
      case "E":
        return Task.createTask(String.format("%s /at %s", descr, date), Task.Type.EVENT);
      case "D":
        return Task.createTask(String.format("%s /by %s", descr, date), Task.Type.DEADLINE);
      default:
        throw new IllegalArgumentException(
          "This task is not correctly stringified. - " + stringifiedTask
        );
    }
  }

  public String taskToString() {
    String type;
    switch (this.type) {
      case EVENT:
        type = "E";
        break;
      case DEADLINE:
        type = "D";
        break;
      case TODO:
        type = "T";
        break;
      default:
        throw new IllegalArgumentException("Task type enums inconsistently applied");
    }
    return String.format("%s%s%s%s", type, DELIMITER, this.title, DELIMITER);
  }

  /**
   * Set a task to be completed.
   *
   * @param isComplete new completion status
   */
  public void markComplete(boolean isComplete) {
    this.isComplete = isComplete;
  }

  @Override
  public String toString() {
    return (isComplete ? "[x] " : "[ ] ") + this.title;
  }
}
