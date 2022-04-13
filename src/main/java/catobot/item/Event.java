package catobot.item;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import catobot.exception.EmptyCommandException;

/**
 * Represents a type of Task which happen at a specific timing.
 */
public class Event extends Task {
    /** The time of the event. */
    private final LocalDateTime at;

    /**
     * Constructor for Event.
     *
     * @param description The details of the event.
     * @param at The time of the event.
     * @throws EmptyCommandException If the description is empty.
     */
    private Event(String description, LocalDateTime at) throws EmptyCommandException {
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
    public static Event of(String description, LocalDateTime at) throws EmptyCommandException {
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
        return String.format("[E]%s (at: %s)", super.toString(), at.format(DATE_FORMAT_DISPLAY));
    }

    /**
     * Represents the format of event in storage.
     *
     * @return The string representation of event in storage.
     */
    @Override
    public String toStringInDoc() {
        String s = super.toStringInDoc();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return String.format("E | %s | %s", s, this.at.format(formatter));
    }

    /**
     * Compares two events chronologically (the latest first).
     *
     * @param o Another task being compared with.
     * @return -1 if the datetime is later than the other
     *         1 if the datetime is earlier than the other
     *         0 if the datetimes are the same.
     */
    @Override
    public int compareTo(Task o) {
        if (o instanceof Event) {
            Event ddl = (Event) o;
            return at.compareTo(ddl.at);
        }
        return super.compareTo(o);
    }
}
