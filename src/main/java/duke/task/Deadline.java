package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected static DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("MMM d yyyy");
    protected LocalDate by;

    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    public LocalDate getBy() {
        return by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(FORMATTER) + ")";
    }

    @Override
    public String toFileFormat() {
        return "D / " + super.toFileFormat() + " / " + by;
    }
}
