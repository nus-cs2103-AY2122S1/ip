package cygnus.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event which is a subtype of Task.
 * An Event encapsulates an additional LocalDateTime
 * which represents the timing of the Event.
 *
 * @author Joshua Yong
 */
public class Event extends Task {

    private static final String dateTimePrintFormat = "EEE dd MMM yyyy hh:mma";

    protected LocalDateTime at;

    /**
     * Class constructor.
     *
     * @param description The given Event description.
     * @param at The timing of the Event.
     */
    public Event(String description, LocalDateTime at) {
        super(description);
        this.at = at;
    }


    /**
     * Returns this Event as a String.
     *
     * @return String representation of the Event.
     */
    @Override
    public String toString() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(dateTimePrintFormat);
        return "[E]" + super.toString() + " | at: " + at.format(dateTimeFormatter);
    }

}
