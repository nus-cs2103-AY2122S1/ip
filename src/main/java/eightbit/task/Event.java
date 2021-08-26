package eightbit.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event with a date/time.
 */
public class Event extends Task {

    protected LocalDateTime at;

    /**
     * Constructs an event.
     *
     * @param description Description of the event.
     * @param at Date and time of the event.
     */
    public Event(String description, LocalDateTime at) {
        super(description);
        this.at = at;
    }

    /**
     * Constructs an event. Marks the event as done or not.
     *
     * @param description Description of the event.
     * @param at Date and time of the event.
     * @param isDone Whether the task is done.
     */
    public Event(String description, LocalDateTime at, boolean isDone) {
        super(description, isDone);
        this.at = at;
    }

    public LocalDateTime getAt() {
        return this.at;
    }

    /**
     * @return String representation of an Event.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm");
        return "[E]" + super.toString() + " (at: " + at.format(formatter) + ")";
    }
}
