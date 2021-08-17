package tasks;

/**
 * Handles tasks that have a single date.
 */
public class DeadlineTask extends Task {

  private String date;

  public DeadlineTask(String title, String date) {
    super(title, Type.DEADLINE);
    this.date = date;
  }

  @Override
  public String toString() {
    return "[D] " + super.toString() + String.format(" (at: %s)", this.date);
  }
}
