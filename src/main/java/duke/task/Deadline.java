package duke.task;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDate by;

    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    @Override
    public String getDetails() {
        return super.getDetails() + " (by: " + getDateString() + ")";
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public String getDateString() {
        return by.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    public LocalDate getDate() {
        return this.by;
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}