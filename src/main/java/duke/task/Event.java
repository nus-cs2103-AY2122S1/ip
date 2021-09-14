package duke.task;

import duke.exceptions.InvalidDescriptionException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Subclass of Task that is at a specific date/time.
 */
public class Event extends Task {

    private LocalDate date;

    /**
     * Constructs an Event object.
     *
     * Formats the String date into LocalDate.
     *
     * @param description Description of the Event.
     * @param date Date of the deadline.
     */
    public Event(String description, String date) throws InvalidDescriptionException {
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
     * @return LocalDate The Date.
     */
    public LocalDate getDate() {
        return this.date;
    }

    /**
     * Returns the string representation of the Event.
     *
     * @return String Representation of Event.
     */
    @Override
    public String toString() {
        return ("[E]" + super.toString()
                + " (at: "
                + this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")");
    }
}
