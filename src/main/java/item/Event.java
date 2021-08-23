package item;

import exception.EmptyCommandException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Event is a type of Task which happen at a specific timing.
 */
public class Event extends Task {
    private LocalDate at;

    private Event(String description, LocalDate at) throws EmptyCommandException {
        super(description);
        this.at = at;
    }

    /**
     * Creates an event.
     *
     * @param description The description of Event details.
     * @param at The time when the Event happens.
     * @return The created Event.
     * @throws EmptyCommandException if the description is empty.
     */
    public static Event of(String description, LocalDate at) throws EmptyCommandException {
        if (description.isEmpty()) {
            throw new EmptyCommandException("event");
        } else {
            return new Event(description, at);
        }
    }

    /**
     * Returns the string representation of Event.
     *
     * @return The string representation of Event, including status, description and time.
     */
    @Override
    public String toString() {
        return "[E]"
                + super.toString()
                + " (at: "
                + at.format(DateTimeFormatter.ofPattern("dd MMM yyyy"))
                + ")";
    }

    @Override
    public String toStringInDoc() {
        String s = super.toStringInDoc();
        String s1 = String.format("E | %s | %s", s, this.at);
        return s1;
    }
}
