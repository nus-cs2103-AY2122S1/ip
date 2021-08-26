package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
    public Event(String description, String date) {
        super(description);
        this.date = LocalDate.parse(date);

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
        return ("[E]" + super.toString() + " (at: " +
                this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")");
    }

}