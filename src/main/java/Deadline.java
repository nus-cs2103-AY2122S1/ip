import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class Deadline extends Task {

  protected LocalDate when;

  public Deadline(String description, String when) {
    super(description);
    this.when = LocalDate.parse(when.trim());
  }

  @Override
  public String toString() {
    return "[D]" + super.toString() + " (by: " + when.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
  }
}
