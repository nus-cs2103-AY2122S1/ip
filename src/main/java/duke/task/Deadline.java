package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Task of type Deadline, which has a due time.
 */
public class Deadline extends Task {
    protected LocalDateTime ddlDateTime;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE, dd/MMM/yyyy hh:mm a");

    /**
     * Creates a Deadline object.
     *
     * @param deadlineName The name of the deadline.
     * @param byTime The due time of the deadline.
     */
    public Deadline(String deadlineName, LocalDateTime byTime) {
        super(deadlineName);
        this.ddlDateTime = byTime;
    }

    /**
     * Returns the name of this Deadline object.
     *
     * @return The Deadline name.
     */
    public String getDeadlineName() {
        return this.taskName;
    }

    /**
     * Returns the date and time of this Deadline object.
     *
     * @return The Deadline date and time.
     */
    public LocalDateTime getDeadlineDateTime() {
        return this.ddlDateTime;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + formatter.format(ddlDateTime) + ")";
    }
}
