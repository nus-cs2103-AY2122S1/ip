package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event, a subtype of Task. An Event also includes information of when the Event is in the form of an
 * Event at, stored as a LocalDateTime.
 *
 * @author Hanif Kamal
 */
public class Event extends Task {
    private LocalDateTime at;

    /**
     * Class constructor.
     *
     * @param taskName The name or description of the Event.
     * @param isDone Whether or not the Event is done.
     * @param at The date and time of the Event.
     */
    public Event(String taskName, boolean isDone, LocalDateTime at) {
        super(taskName, isDone);
        this.at = at;
    }

    /**
     * Returns the string representation of the Event.
     *
     * @return The string representation of the Event.
     */
    @Override
    public String toString() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd MMM yyy hh:mm a");
        return "[E]" + super.toString() + " (at: " + this.at.format(dtf) + ")";
    }
}
