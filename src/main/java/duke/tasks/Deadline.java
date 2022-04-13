package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline entered by the user.
 */
public class Deadline extends Task {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
    protected final LocalDate by;

    /**
     * Constructor for the Deadline.
     *
     * @param description Description of the deadline.
     * @param by The date the deadline is due.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDate.parse(by, formatter);
    }

    public LocalDate getDateBy() {
        return by;
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + by.format(formatter) + ")";
    }
}
