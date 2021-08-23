import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    public Deadline(String description, LocalDate deadline) {
        super(String.format(
            "%s (by: %s)",
            description,
            deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
        ));
    }

    public String getTaskIcon() {
        return "D";
    }
}
