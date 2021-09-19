package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Task of type Event, which has a time at which it happens.
 */
public class Event extends Task {
    protected LocalDateTime eveDateTime;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE, dd/MMM/yyyy hh:mm a");

    /**
     * Creates an Event object.
     *
     * @param eventName The name of the event.
     * @param atTime The time the event happens.
     */
    public Event(String eventName, LocalDateTime atTime) {
        super(eventName);
        this.eveDateTime = atTime;
    }

    /**
     * Returns the name of this Event object.
     *
     * @return The Event name.
     */
    public String getEventName() {
        return this.taskName;
    }

    /**
     * Returns the date and time of this Event object.
     *
     * @return The Event date and time.
     */
    public LocalDateTime getEventDateTime() {
        return this.eveDateTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + formatter.format(eveDateTime) + ")";
    }
}
