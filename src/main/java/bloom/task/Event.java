package bloom.task;

import java.time.LocalDateTime;

/**
 * Represents an event task.
 */
public class Event extends Task {

    /** The date and time of the event. */
    protected final LocalDateTime at;

    /**
     * Constructor for an Event.
     *
     * @param description the description of the event
     * @param at          the date and time of the event
     */
    public Event(String description, LocalDateTime at) {
        super(description);
        this.at = at;
    }

    /**
     * Gets the string representation of the event.
     *
     * @return a string representing the event
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.at + ")";
    }

    /**
     * Converts to the format used for database storage.
     *
     * @return a string representing the event
     */
    @Override
    public String toDb() {
        return "E | " + super.toDb() + " | " + this.at;
    }
}
