import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * This Deadline class implements the characteristics of a task
 * that needs to be done before a specific date/time.
 *
 * @author Yeo Jun Wei
 */
public class Deadline extends Task {

    /** The date/time that the task needs to be done by */
    protected LocalDate by;

    /**
     * Constructor for a Deadline instance that takes in a description and deadline.
     *
     * @param description The given task description.
     * @param by The deadline of the given task.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the string representation of a Deadline instance.
     *
     * @return A string representing a Deadline instance.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}