package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A Task of type Deadline.
 */
public class Deadline extends Task {

    /** The deadline date time */
    protected LocalDateTime date;

    /**
     * The Deadline constructor.
     * @param description The description of the task.
     * @param date The deadline of the task.
     */
    public Deadline(String description, String date) {
        super(description);
        this.date = LocalDateTime.parse(date);
    }

    /**
     * Returns the string representation of a Deadline.
     * @return string representation of a Deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString()
                + " (by: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")) + ")";
    }
}
