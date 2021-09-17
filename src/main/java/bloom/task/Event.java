package bloom.task;

import java.time.LocalDateTime;

/**
 * Represents an event task.
 */
public class Event extends Task {

    /** The date and time of the event. */
    protected final LocalDateTime at;

    /**
     * Constructor for an Event.
     *
     * @param description the description of the event
     * @param at          the date and time of the event
     */
    public Event(String description, LocalDateTime at) {
        super(description);
        this.at = at;
    }

    /**
     * Gets the string representation of the event.
     *
     * @return a string representing the event
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + printDate() + ")";
    }

    /**
     * Converts to the format used for database storage.
     *
     * @return a string representing the event
     */
    @Override
    public String toDb() {
        return "E | " + super.toDb() + " | " + this.at;
    }


    public LocalDateTime getDate() {
        return at;
    }

    /**
     * Prints date of an event.
     *
     * @return a string date of format MMM dd yyyy
     */
    @Override
    public String printDate() {
        int y = at.getYear();
        int m = at.getMonthValue();
        int d = at.getDayOfMonth();

        String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun",
                "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        String month = months[m - 1];

        return month + " " + d + " " + y + " " + at.getHour() + ":" + at.getMinute();
    }
}
