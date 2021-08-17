package tasks;

/**
 * Handles tasks that have a start and end date.
 */
public class EventTask extends Task {

  private String dates;

  public EventTask(String title, String dates) {
    super(title, Type.EVENT);
    this.dates = dates.trim();
  }

  @Override
  public String toString() {
    return "[E] " + super.toString() + String.format(" (at: %s)", this.dates);
  }
}
