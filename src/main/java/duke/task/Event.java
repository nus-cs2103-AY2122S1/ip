package duke.task;

import java.time.LocalDateTime;


/**
 * Represents an event
 */
public class Event extends TaskWithTime {
    protected LocalDateTime at;

    /**
     * Class Constructor
     *
     * @param description description of event
     * @param at time of event
     */
    public Event(String description, LocalDateTime at) {
        super(description);
        this.at = at;
    }

    /**
     * Modifies the string representation of an event object
     *
     * @return string representation of the event
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at.format(super.outputFormatter) + ")";
    }
}
