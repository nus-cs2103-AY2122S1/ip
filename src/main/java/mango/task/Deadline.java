package mango.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that is a deadline. A <code>Deadline</code> object corresponds to a
 * <code>Task</code> that has a description, a completion status, and a date by which the task should be completed.
 */
public class Deadline extends Task {
    protected LocalDate date;

    /**
     * Constructor for a Deadline.
     *
     * @param description The description of the deadline.
     * @param date The date on which the deadline falls in yyyy-mm-dd format.
     * @param tag The tag attached to this deadline.
     */
    public Deadline(String description, String date, String tag) {
        super(description, tag, false);
        this.date = LocalDate.parse(date);
    }

    /**
     * Constructor for a Deadline, initialised with a completion status.
     *
     * @param description The description of the deadline.
     * @param date The date on which the deadline falls in yyyy-mm-dd format.
     * @param status The completion status of the deadline.
     * @param tag The tag attached to this deadline.
     */
    public Deadline(String description, String date, String status, String tag) {
        super(description, tag, status.equals("true"));
        this.date = LocalDate.parse(date);
    }

    /**
     * Returns the string representation of the type of task.
     *
     * @return The string representation of the type of task.
     */
    @Override
    public String getType() {
        return "D";
    }

    /**
     * Returns the description of the deadline.
     *
     * @return The description of the deadline.
     */
    @Override
    public String getDescription() {
        return String.format("%s (by: %s)", this.description, this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }

    /**
     * Returns the string representation of the deadline for saving.
     *
     * @return The string representation of the deadline.
     */
    @Override
    public String getSaveFormatString() {
        return String.format("%s:%s:%s:%s:%s\n", this.getType(), this.getStatus(), this.description, this.date, this.tag);
    }
}
