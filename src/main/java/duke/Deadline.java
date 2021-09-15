package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A Task of type Deadline.
 */
public class Deadline extends Task {

    /** The deadline date time */
    protected LocalDateTime date;

    /** The save-friendly String representation of the date */
    protected String dateString;

    /**
     * The Deadline constructor.
     * @param description The description of the task.
     * @param date The deadline of the task.
     */
    public Deadline(String description, String date) {
        super(description);
        this.date = LocalDateTime.parse(date);
        this.dateString = date;
    }

    /**
     * Returns the string representation of a Deadline task in the saved file.
     * @return The string representation of a Deadline task in the saved file.
     */
    @Override
    public String savedToString() {
        String doneStatus = super.isDone ? "1" : "0";
        return "D | " + doneStatus + " | " + super.description + " | " + dateString;
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
