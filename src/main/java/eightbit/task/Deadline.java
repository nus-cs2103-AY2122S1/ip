package eightbit.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {

    protected LocalDateTime by;

    /**
     * Constructs a task with a deadline.
     *
     * @param description Description of the task.
     * @param by          Date and time of the deadline.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Constructs a task with a deadline.
     * Marks the task as done or not. Adds a list of tags to the task.
     *
     * @param description Description of the task.
     * @param by          Date and time of the deadline.
     * @param isDone      Whether the task is done.
     * @param tags        List of tags.
     */
    public Deadline(String description, LocalDateTime by, boolean isDone, List<String> tags) {
        super(description, isDone, tags);
        this.by = by;
    }

    public LocalDateTime getBy() {
        return this.by;
    }

    /**
     * Returns the string representation of a Deadline.
     *
     * @return String representation of a Deadline.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm");
        return "[D]" + super.toString() + " (by: " + by.format(formatter) + ")";
    }
}
