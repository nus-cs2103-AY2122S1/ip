package tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The Event class encapsulates all the details of each event.
 *
 * @author Quan Teng Foong
 */
public class Event extends Task implements Recurring {
    private final LocalDate eventDate;
    private final Recurrence recurrence;

    /**
     * Constructor for Event object.
     *
     * @param message name of Event
     * @param eventDate date of Event
     */
    public Event(String message, String eventDate) {
        super(message);
        //TODO: consider putting this split method in Parser
        String[] eventDateAndRecurrence = eventDate.split("/recur ");
        this.eventDate = LocalDate.parse(eventDateAndRecurrence[0].trim());
        if (eventDateAndRecurrence.length == 2) {
            this.recurrence = Recurring.stringToRecurrence(eventDateAndRecurrence[1].trim());
        } else {
            this.recurrence = Recurring.stringToRecurrence("");
        }
    }

    /**
     * Overrides toString() method.
     * @return String representation of the Event object.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: "
                + eventDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ") "
                + Recurring.recurrenceToString(this.recurrence);
    }

    /**
     * Converts contents to a storable String.
     * TODO: manage storing of recurring Events
     * @return a String that represents this Event in storage
     */
    @Override
    public String toStorage() {
        return "E|" + super.toStorage() + "/at " + this.eventDate;
    }
}
