import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class Deadline extends Task {

  protected LocalDate when;

  public Deadline(String description, String when, boolean done) {
    super(description, Task.Type.DEADLINE, done);
    this.when = LocalDate.parse(when.trim());
  }

  @Override
  public String taskToString() {
    return super.taskToString() + when.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
  }

  @Override
  public String toString() {
    return "[D]" + super.toString() + " (by: " + when.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
  }
}
