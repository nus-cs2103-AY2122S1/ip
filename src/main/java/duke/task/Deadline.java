package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * This Deadline class implements the characteristics of a task
 * that needs to be done before a specific date/time.
 */
public class Deadline extends Task {

    protected LocalDate timeBy;

    /**
     * Constructor for a Deadline instance that takes in a description and deadline.
     *
     * @param description The given task description.
     * @param timeBy The deadline of the given task.
     */
    public Deadline(String description, LocalDate timeBy) {
        super(description);
        this.timeBy = timeBy;
    }

    /**
     * Returns the deadline of the current task.
     *
     * @return A LocalDate instance representing the deadline.
     */
    public LocalDate getDate() {
        return this.timeBy;
    }

    /**
     * Returns the string representation of a Deadline instance.
     *
     * @return A string representing a Deadline instance.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + timeBy.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
