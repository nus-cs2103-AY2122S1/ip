package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A class to represent an Event task.
 */
public class Event extends Task {

    private LocalDateTime at;

    /**
     * Constructor for an Event object.
     *
     * @param description name of the task
     * @param at the date and time the event happens
     */
    public Event(String description, LocalDateTime at) {
        super(description);
        this.at = at;
    }

    /**
     * Returns the event information
     *
     * @return the event information
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
                + at.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + ")";
    }
}
