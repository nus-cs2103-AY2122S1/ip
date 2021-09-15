package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 * A Task of type event
 */
public class Event extends Task {

    /** The event date time */
    protected LocalDateTime date;

    /** The String representation of a save-friendly date */
    protected String dateString;

    /**
     * The Event constructor.
     * @param description The description of the task.
     * @param date The time of the event.
     */
    public Event(String description, String date) {
        super(description);
        this.date = LocalDateTime.parse(date);
        this.dateString = date;
    }

    /**
     * Returns the string representation of an Event task in the saved file.
     * @return The string representation of an Event task in the saved file.
     */
    @Override
    public String savedToString() {
        String doneStatus = super.isDone ? "1" : "0";
        return "E | " + doneStatus + " | " + super.description + " | " + dateString;
    }

    /**
     * Returns the string representation of an Event
     * @return the string representation of an Event
     */
    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (at: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")) + ")";
    }
}
