package tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

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
        LocalDateTime startDate;
        LocalDateTime endDate;
        try {
          String[] arr = args[1].split(" - ");
          if (arr.length != 2) {
            DateParser.parseDateTimeInput(""); // throws error
          }
          startDate = DateParser.parseDateTimeInput(arr[0]);
          endDate = DateParser.parseDateTimeInput(arr[1]);
        } catch (DateTimeParseException e) {
          throw new InvalidTaskException(
            "The two dates for deadline creation could not be parsed. Expected:\n" +
            "2 dates separated by ' - '. Dates come in the forms: " +
            "'YYYY-MM-DD' or 'YYYY-MM-DD HHMM' where time comes in 24 hour format."
          );
        }
        return new EventTask(args[0].trim(), startDate, endDate);
      case DEADLINE:
        args = inputString.split(" /by ");
        if (args.length != 2) {
          throw new InvalidTaskException("Expected '{title} /by {dates}' for deadline tasks");
        }
        LocalDateTime date;
        try {
          date = DateParser.parseDateTimeInput(args[1].trim());
        } catch (DateTimeParseException e) {
          throw new InvalidTaskException(
            "Date for event creation could not be parsed. Expected:\n" +
            "'YYYY-MM-DD' or 'YYYY-MM-DD HHMM' where time comes in 24 hour format."
          );
        }
        return new DeadlineTask(args[0].trim(), date);
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
