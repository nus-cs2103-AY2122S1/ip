package tasks;

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
    String[] args;
    switch (type) {
      case TODO:
        return new TodoTask(inputString);
      case EVENT:
        args = inputString.split("/at");
        if (args.length != 2) {
          throw new IllegalArgumentException("Expected '{title} /at {date}' for event tasks");
        }
        return new EventTask(args[0], args[1]);
      case DEADLINE:
        args = inputString.split("/by");
        if (args.length != 2) {
          throw new IllegalArgumentException("Expected '{title} /by {dates}' for deadline tasks");
        }
        return new DeadlineTask(args[0], args[1]);
      default:
        throw new IllegalArgumentException("Task type not expected.");
    }
  }

  protected Task(String title, Type type) {
    this.title = title;
    this.type = type;
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
