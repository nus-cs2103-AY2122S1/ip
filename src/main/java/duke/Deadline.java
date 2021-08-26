package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Subclass of Task that needs to be done by a specific date/time.
 */
public class Deadline extends Task {

    private LocalDate date;

    /**
     * Constructs a Deadline object.
     *
     * Formats the String date into LocalDate.
     *
     * @param description Description of the Deadline.
     * @param date Date of the deadline.
     */
    public Deadline(String description, String date) {
        super(description);
        this.date = LocalDate.parse(date);
    }

    /**
     * Gets the date of the Deadline.
     *
     * @return LocalDate The date.
     */
    public LocalDate getDate() {
        return this.date;
    }

    /**
     * Returns the string representation of the Deadline.
     *
     * @return String Representation of Deadline.
     */
    @Override
    public String toString() {
        return ("[D]" + super.toString() + " (by: " +
                this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")");
    }

}