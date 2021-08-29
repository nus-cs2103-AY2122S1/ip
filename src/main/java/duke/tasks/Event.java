package duke.tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *  The event class is a subclass of Task representing an event
 */
public class Event extends Task {

    protected LocalDateTime at;

    /**
     * public constructor which initialises the description and end date and time of an event
     * @param description description of event
     * @param at end date of event
     */
    public Event(String description, LocalDateTime at) {
        super(description);
        this.at = at;
    }

    @Override
    public String getDetails() {
        return super.toString() + " (at: " + getDateString() + ")";
    }

    /**
     * Converts the Event's date and time into a String
     *
     * @return String representation of the date and time
     */
    @Override
    public String getDateString() {
        return at.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm"));
    }

    public LocalDate getDate() {
        return this.at.toLocalDate();
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return "[E] " + super.toString() + " (at: " + at.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm")) + ")";
    }
}