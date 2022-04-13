package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deadlines are a type of task, where you can add a date
 * representing the latest date of when the task should be completed.
 */
public class Deadline extends Task {

    /**
     * The associated date for the deadline
     **/
    protected LocalDate by;

    /**
     * Constructs a deadline.
     *
     * @param description The description associated with the deadline.
     * @param by          The date by which the deadline should be completed.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the type of task.
     *
     * @return "D" representing the task is a deadline.
     */
    @Override
    public String getTaskType() {
        return "D";
    }

    /**
     * Returns the timing of the task.
     *
     * @return The date associated with the deadline.
     */
    @Override
    public LocalDate getTiming() {
        return by;
    }

    /**
     * Pretty-prints the deadline in a readable way.
     *
     * @return The string representation of the deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }

}
