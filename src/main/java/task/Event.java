package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * Event represents an event task.
 *
 * @author Ho Wen Zhong
 */
public class Event extends Task {

    protected LocalDate at;

    /**
     * Constructs an Event object.
     *
     * @param description description of event.
     * @param at date of event.
     */
    public Event(String description, LocalDate at) {
        super(description);
        this.at = at;
    }

    /**
     * Returns the String representation of the Event.
     *
     * @return String representation of Event
     */
    @Override
    public String toString() {
        String formattedDate = at.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "[E]" + super.toString() + " (at: "
                + formattedDate + ")";
    }

    /**
     * Returns the event date.
     *
     * @return Event date.
     */
    public LocalDate getAt() {
        return this.at;
    }
}
