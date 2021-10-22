package captain.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import captain.DukeException.MissingDescriptionException;

/**
 * Represents an event with a date.
 *
 * @author Adam Ho
 */
public class Event extends Task {
    protected LocalDate at;

    /**
     * Creates an event with the specified description and date.
     * @param description The task description.
     * @param at The date of event.
     */
    public Event(String description, LocalDate at) throws MissingDescriptionException {
        super(description);
        this.at = at;
    }

    public LocalDate getAtDate() {
        return at;
    }

    /**
     * Represents event object as a string.
     * @return A string representation of the event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
