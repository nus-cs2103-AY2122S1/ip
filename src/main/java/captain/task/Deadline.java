package captain.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import captain.DukeException.MissingDescriptionException;

/**
 * Represents a task with deadline.
 *
 * @author Adam Ho
 */
public class Deadline extends Task {
    protected LocalDate by;

    /**
     * Creates a task with the specified deadline.
     * @param description The task description.
     * @param by The deadline of the task.
     */
    public Deadline(String description, LocalDate by) throws MissingDescriptionException {
        super(description);
        this.by = by;
    }

    public LocalDate getByDate() {
        return by;
    }

    /**
     * Represents deadline task as a string.
     * @return A string representation of the deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
