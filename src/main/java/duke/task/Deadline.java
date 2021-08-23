package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
  protected LocalDate by;

  public Deadline(String description, LocalDate by) {
    super(description);
    this.by = by;
  }

  @Override
  public String toStringData() {
    return "D | " + super.toStringData() + " | " + this.by;
  }

  @Override
  public String toString() {
    return "[D]"
        + super.toString()
        + " (by: " + this.by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
  }
}
