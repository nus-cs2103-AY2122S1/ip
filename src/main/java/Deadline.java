import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline. A subclass of the Task class.
 */
public class Deadline extends Task {
    /** Deadline of this task as a local date */
    protected LocalDateTime time;

    /**
     * Constructor of the class `Deadline`.
     *
     * @param description Description of the task.
     * @param by Deadline of the task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.time = LocalDateTime.parse(by.trim(), Task.formatter);
    }

    /**
     * Converts a task with deadline to a string.
     *
     * @return The string representation of a task with deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " +
                this.time.format(DateTimeFormatter.ofPattern("MMM d yyyy, HH:mm")) + ")";
    }
}
