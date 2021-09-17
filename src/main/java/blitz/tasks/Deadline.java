package blitz.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    private LocalDateTime by;

    /**
     * Creates a Deadline with the given description, date and time by
     * which the task is to be completed.
     *
     * @param description mentions what the task to be completed is.
     * @param by date and time by which the task is to be completed.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns date and time by which this task must be completed.
     *
     * @return date and time by which this task must be completed.
     */
    public String getBy() {
        return this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a"));
    }

    /**
     * Returns string representation of Deadline object.
     *
     * @return string representation of Deadline object.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
            + this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a")) + ")";
    }

}
