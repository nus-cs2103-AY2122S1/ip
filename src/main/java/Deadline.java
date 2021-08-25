import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

  protected String by;
  private LocalDate time;
  
  public Deadline(String description, String by) {
    super(description);
    this.time = LocalDate.parse(by);
    this.by = this.time.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    
  }

  @Override
  public String toString() {
    return "[D]" + super.toString() + " (by: " + this.by + ")";
  }
}