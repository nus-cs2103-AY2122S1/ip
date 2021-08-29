package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected final LocalDate by;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");

    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDate.parse(by, formatter);
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + by.format(formatter) + ")";
    }
}
