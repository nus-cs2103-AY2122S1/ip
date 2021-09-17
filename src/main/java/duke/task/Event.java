package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The Event class encapsulates an event class.
 */
public class Event extends Task {

    /** The date and time the event will be taking place. */
    protected LocalDateTime eventTime;

    /**
     * Constructor to initialise an Event task.
     *
     * @param description The description of the Event task.
     * @param eventTime The date and time for the Event task.
     */
    public Event(String description, LocalDateTime eventTime) {
        super(description);
        this.eventTime = eventTime;
    }

    /**
     * Gets the at description for the Event task.
     *
     * @return The at description for the Event task.
     */
    public LocalDateTime getAt() {
        return this.eventTime;
    }

    /**
     * Returns the string representation of the Event task.
     *
     * @return The string representation of the Event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
                + eventTime.format(DateTimeFormatter.ofPattern("MM dd yyyy 'at' hh:mm")) + ")";
    }
}
