package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Class that stores a Task that contains a date(by).
 */
public class Deadline extends Task {

    protected LocalDate by;

    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toData() {
        return "D|" + super.toData() + "|" + this.by + "\n";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format((DateTimeFormatter.ofPattern("MMM d yyyy"))) + ")";
    }
}
