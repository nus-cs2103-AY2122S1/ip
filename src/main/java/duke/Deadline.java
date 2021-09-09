package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline that consists of a task to be completed by a specified date and time.
 */
public class Deadline extends Task {

    protected LocalDateTime dateTime;

    /**
     * Class constructor that constructs a Deadline Object.
     *
     * @param description Description of the deadline task.
     * @param dateTime Date and time for the task to be completed by.
     */
    public Deadline(String description, String dateTime) {
        super(description);

        DateTimeFormatter scanned = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        this.dateTime = LocalDateTime.parse(dateTime, scanned);
    }

    /**
     * Returns a String representation of Deadline.
     *
     * @return String representation of Deadline.
     */
    @Override
    public String toString() {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd MMM yyyy hh:mma");
        return "[D]" + super.toString() + " (by: " + this.dateTime.format(dateFormat) + ")";
    }
}
