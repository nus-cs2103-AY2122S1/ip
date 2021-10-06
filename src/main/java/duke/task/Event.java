package duke.task;

import java.time.LocalDateTime;


/**
 * Represents an event
 */
public class Event extends TaskWithTime {
    /**
     * Class Constructor
     *
     * @param description description of event
     * @param at time of event
     */
    public Event(String description, LocalDateTime at) {
        super(description, at);
    }

    /**
     * Modifies the string representation of an event object
     *
     * @return string representation of the event
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + super.dateTime.format(super.outputFormatter) + ")";
    }
}
