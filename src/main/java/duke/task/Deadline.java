package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.exceptions.InvalidDescriptionException;

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
    public Deadline(String description, String date) throws InvalidDescriptionException {
        super(description);
        try {
            this.date = LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            throw new InvalidDescriptionException("Time Format is wrong, please specify as YYYY-MM-DD");
        }
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
        return ("[D]" + super.toString() + " (by: "
                + this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")");
    }
}
