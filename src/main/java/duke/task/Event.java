package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event object.
 *
 * @author: James Kua
 * @version: Duke-Level-8
 */
public class Event extends Task {

    /** String that specifies the time of event. */
    protected String at;
    /** String that specifies the date of event. */
    protected LocalDate date;
    /** String that specifies timing of the event. */
    protected LocalTime time;

    /**
     * Constructor for creating an event task.
     *
     * @param description The event's description.
     * @param date Date that event is scheduled.
     */
    public Event(String description, LocalDate date) {
        super(description);
        this.date = date;
    }

    /**
     * Constructor for creating an event task.
     *
     * @param description The event's description.
     * @param at Time that the event is scheduled.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Constructor for creating an event task.
     *
     * @param description The event's description.
     * @param date Date that the event is scheduled.
     * @param time Time that the event is scheduled.
     */
    public Event(String description, LocalDate date, LocalTime time) {
        super(description);
        this.date = date;
        this.time = time;
    }

    /**
     * Returns time to the event.
     *
     * @return String representing the time of the event.
     */
    public String getTime() {
        return at;
    }

    /**
     * Formats the event based on the user's input for saving.
     *
     * @return The formatted String of the event for saving.
     */
    public String formatSave() {
        if (time != null) {
            return "E | " + ((super.isDone) ? "1 |" : "0 | ") + super.getDescription() + " | " +
                    date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ", " + time;
        } else if (date != null) {
            return "E | " + ((super.isDone) ? "1 |" : "0 | ") + super.getDescription() + " | " +
                    date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        }
        return "E | "  + ((super.isDone) ? "1 |" : "0 | ") + super.getDescription() + " | " + getTime();
    }

    @Override
    public String toString() {
        if (time != null) {
            return "[E]" + super.toString() + " (at: " + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))
                    + ", " + time + ")";
        } else if (time != null) {
            return "[E]" + super.toString() + " (at: " + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
        } else {
            return "[D]" + super.toString() + " (by: " + at + ")";
        }
    }
}
