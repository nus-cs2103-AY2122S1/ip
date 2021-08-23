import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class Event extends Task {

  protected LocalDate when;

  public Event(String description, String when, boolean done) {
    super(description, Task.Type.EVENT, done);
    this.when = LocalDate.parse(when.trim());
  }

  @Override
  public String taskToString() {
    return super.taskToString() + when.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
  }

  @Override
  public String toString() {
    return "[E]" + super.toString() + " (at: " + when.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
  }
}
