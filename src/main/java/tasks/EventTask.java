package tasks;

import java.time.LocalDateTime;

/**
 * Handles tasks that have a start and end date.
 */
public class EventTask extends Task {

  private LocalDateTime startDateTime;
  private LocalDateTime endDateTime;

  public EventTask(String title, LocalDateTime startDateTime, LocalDateTime endDateTime) {
    super(title, Type.EVENT);
    this.startDateTime = startDateTime;
    this.endDateTime = endDateTime;
  }

  @Override
  public String taskToString() {
    return super.taskToString() + this.dates;
  }

  @Override
  public String toString() {
    return (
      "[E] " +
      super.toString() +
      String.format(
        " (at: %s - %s)",
        DateParser.toHumanReadable(startDateTime),
        DateParser.toHumanReadable(endDateTime)
      )
    );
  }
}
