package seedu.duke.task;

import java.time.LocalDate;

/**
 * Represents an Event task. An <code>Event</code> describes
 * the task and the date that the event would occur.
 */
public class Event extends Task {
    /**
     * String that indicates the time of the event.
     */
    protected LocalDate timeOfEvent;

    /**
     * Public constructor to create an <code>Event</code>
     * object.
     *
     * @param description Description of the event.
     * @param timeOfEvent Time of the event.
     */
    public Event(String description, LocalDate timeOfEvent) {
        super(description);
        this.timeOfEvent = timeOfEvent;
    }

    /**
     * String representation of an <code>Event</code>.
     *
     * @return String representation of an <code>Event</code>.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.timeOfEvent +")";
    }

}
