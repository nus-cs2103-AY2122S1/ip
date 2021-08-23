import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class Event extends Task {

  protected LocalDate when;

  public Event(String description, String when) {
    super(description);
    this.when = LocalDate.parse(when.trim());
  }

  @Override
  public String toString() {
    return "[E]" + super.toString() + " (at: " + when.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
  }
}
