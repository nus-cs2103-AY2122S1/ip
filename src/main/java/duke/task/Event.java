package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A class which encapsulates an event type task.
 */

public class Event extends Task {

    /** The date and time of the event */
    protected LocalDateTime at;

    /**
     * A public constructor to initialise the date and time
     * to the given one and calls the parent constructor
     * with the description of the event.
     * @param description The description of the event.
     * @param at The date and time of the event.
     */
    public Event(String description, LocalDateTime at) {
        super(description);
        this.at = at;
    }

    public LocalDateTime getAt() {
        return at;
    }

    public String getFormattedAt() {
        return at.format(DateTimeFormatter.ofPattern(
                "MMM d yyyy ',' HH:mm"));
    }

    /**
     * Returns the letter which indicates the type of the
     * task.
     *
     * @return The letter E to represent event.
     */
    @Override
    public String getTaskIndicator() {
        return "E";
    }

    /**
     * Returns the string representation of the event.
     *
     * @return The string representing the event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
                + getFormattedAt() + ")";
    }

}
