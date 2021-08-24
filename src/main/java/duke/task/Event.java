package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event which is a subtype of Task.
 * An event encapsulates an additional LocalDateTime
 * which represents the timing of the event.
 *
 * @author Joshua Yong
 */
public class Event extends Task {

    protected LocalDateTime at;

    /**
     * Class constructor.
     *
     * @param description The given event description.
     * @param at The timing of the event.
     */
    public Event(String description, LocalDateTime at) {
        super(description);
        this.at = at;
    }


    /**
     * Outputs this event as a String.
     *
     * @return String representation of the event.
     */
    @Override
    public String toString() {
        DateTimeFormatter printFormat = DateTimeFormatter.ofPattern("EEE dd MMM yyyy hh:mma");
        return "[E]" + super.toString() + " | at: " + at.format(printFormat);
    }

}
