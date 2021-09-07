package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * Deadline represents a deadline task.
 *
 * @author Ho Wen Zhong
 */
public class Deadline extends Task {

    protected LocalDate by;

    /**
     * Constructs a Deadline object.
     *
     * @param description description of the deadline.
     * @param by date of the deadline.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the String representation of the Deadline.
     *
     * @return String representatin of Deadline.
     */
    @Override
    public String toString() {
        String formattedDate = by.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "[D]" + super.toString() + " (by: "
                + formattedDate + ")";
    }

    /**
     * Returns the deadline date.
     *
     * @return Deadline date.
     */
    public LocalDate getBy() {
        return this.by;
    }
}
