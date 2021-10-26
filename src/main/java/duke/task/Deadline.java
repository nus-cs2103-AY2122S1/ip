package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline with a description and a 'done' status and a Deadline due date.
 */
public class Deadline extends Task {

    protected LocalDate by;

    /**
     * The Constructor for a Deadline object.
     * @param description The description of the Deadline.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Retrieves the date that the Deadline is due.
     *
     * @return The date that the Deadline is due.
     */
    @Override
    public LocalDate getDate() {
        return by;
    }

    /**
     * Converts the Deadline into the specified format for
     * the text file from Storage.
     *
     * @return The file format representation of the Deadline.
     */
    @Override
    public String toFileFormat() {

        char done = '0';

        if (super.isDone) {
            done = '1';
        }
        return "D | " + done + " | " + getDescription() + " | " + getDate();
    }

    /**
     * Returns a string representation of the Event. Appends a
     * "[D]" to indicate it is a Deadline and the date in an "MMM d yyyy"
     * format.
     *
     * @return A string representation of the Deadline.
     */
    @Override
    public String toString() {
        return " [D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

}
