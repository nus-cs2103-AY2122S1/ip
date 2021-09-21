package lania.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents the task class with a deadline.
 */
public class Deadline extends Task {

    /** Deadline of a task */
    protected String by;
    /** Formatted deadline of a task */
    protected LocalDateTime dateTime;

    /**
     * Constructor for Deadline which consists of description and deadline of task.
     *
     * @param description The name of the deadline.
     * @param by The deadline of the task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        this.dateTime = LocalDateTime.parse(by, formatter);
    }

    /**
     * String representation of the deadline object
     * to be stored in the hard disk.
     *
     * @return A string representation of the deadline object.
     */
    public String getStringFormat() {
        return "D|" + this.getStatusIcon() + "|" + this.description + "|" + this.by + "\n";
    }

    /**
     * String representation of the deadline object
     * to be displayed to the user.
     *
     * @return Another string representation of the deadline object.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + dateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy h:mma")) + ")";
    }
}
