package eightbit.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Represents an event with a date/time.
 */
public class Event extends Task {

    protected LocalDateTime at;

    /**
     * Constructs an event.
     *
     * @param description Description of the event.
     * @param at          Date and time of the event.
     */
    public Event(String description, LocalDateTime at) {
        super(description);
        this.at = at;
    }

    /**
     * Constructs an event.
     * Marks the event as done or not. Adds a list of tags to the task.
     *
     * @param description Description of the event.
     * @param at          Date and time of the event.
     * @param isDone      Whether the task is done.
     * @param tags        List of tags.
     */
    public Event(String description, LocalDateTime at, boolean isDone, List<String> tags) {
        super(description, isDone, tags);
        this.at = at;
    }

    public LocalDateTime getAt() {
        return this.at;
    }

    /**
     * Returns the string representation of an Event.
     *
     * @return String representation of an Event.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm");
        return "[E]" + super.toString() + " (at: " + at.format(formatter) + ")";
    }
}
