package botto.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Format for the Botto bot's event task
 */
public class Event extends Task{

    protected LocalDateTime at;

    /**
     * Constructor for an event
     *
     * @param description description of the event
     * @param at datetime of the event
     */
    public Event(String description, LocalDateTime at) {
        super(description);
        this.at = at;
    }

    /**
     * return string representation of the task
     *
     * @return string representation of the task
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy h:mm a");
        return "[E]" + super.toString() + " (at: " + formatter.format(this.at) + ")";
    }
}
