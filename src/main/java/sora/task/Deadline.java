package sora.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline.
 * A deadline contains extra information regarding the date and time of deadline.
 *
 * @author Zhang Shi Chen
 */
public class Deadline extends Task {
    protected final LocalDateTime dateTime;

    /**
     * Constructor for deadline.
     *
     * @param description Description of the deadline
     * @param dateTime Due date and time of the deadline
     */
    public Deadline(String description, LocalDateTime dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    @Override
    LocalDateTime getDateTime() {
        return dateTime;
    }

    /**
     * Formats deadline in the form of: [D][ ] description (by: MMM d yyyy, h:mm a)
     *
     * @return A string representation of the deadline
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + dateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a")) + ")";
    }
}
