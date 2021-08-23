import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that needs to be done before a specific date/time.
 *
 * @author felix-ong
 */
public class Deadline extends Task {
    /** Date/time of the deadline */
    protected LocalDate by;

    /**
     * Constructor of a Deadline Task.
     *
     * @param description Short description of task.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the string representation of a Deadline Task.
     *
     * @return The string representation of a Deadline Task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by.format(DateTimeFormatter.ofPattern("dd MMM yyyy")) + ")";
    }
}
