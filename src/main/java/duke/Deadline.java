package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A class to represent a Deadline task.
 */
public class Deadline extends Task {
    /**
     * The date and time the deadline task is due.
     */
    private LocalDateTime by;

    /**
     * Constructor for a Deadline object.
     *
     * @param description name of the task
     * @param by the date and time the deadline is due
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the deadline information
     *
     * @return the deadline information
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + ")";
    }

    /**
     * Checks if the Task is an EmptyTask.
     *
     * @return true if this Task is an EmptyTask, false otherwise
     */
    @Override
    public boolean isEmptyTask() {
        return false;
    }
}
