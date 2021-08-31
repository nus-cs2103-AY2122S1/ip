package tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The Event class encapsulates all the details of each event.
 *
 * @author Quan Teng Foong
 */
public class Event extends Task {
    private final LocalDate eventDate;

    /**
     * Constructor for Event object.
     *
     * @param message name of Event
     * @param eventDate date of Event
     */
    public Event(String message, String eventDate) {
        super(message);
        this.eventDate = LocalDate.parse(eventDate);
    }

    /**
     * Overrides toString() method.
     * @return String representation of the Event object.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: "
                + eventDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    /**
     * Converts contents to a storable String.
     *
     * @return a String that represents this Event in storage
     */
    @Override
    public String toStorage() {
        return "E|" + super.toStorage() + "/at " + this.eventDate;
    }
}
