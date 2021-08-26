package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
/**
 * A Task of type event
 */
public class Event extends Task {

    /**
     * When is the event?
     */
    protected LocalDateTime at;

    /**
     * The Constructor
     * @param description The description of the task
     * @param at The time of the event, start and end.
     */
    public Event(String description, String at) {
        super(description);
        this.at = LocalDateTime.parse(at);
    }

    /**
     * Returns the string representation of an Event
     * @return the string representation of an Event
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")) + ")";
    }
}
