package neo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 *  This class represents an Event.
 *  An event: tasks that start at a specific time and ends at a specific time.
 *
 * @author Ryan Tian Jun.
 */
public class Event extends Task {

    private String at;

    /**
     * This constructor handles the creation af a new Event Task.
     *
     * @param description Task description.
     * @param type Task type: Event.
     * @param at Event timeline yyyy-mm-dd.
     */
    public Event(String description, Type type, String at) {
        super(description, type);
        try {
            LocalDate d1 = LocalDate.parse(at);
            this.at = d1.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        } catch (DateTimeParseException dateTimeParseException) {
            this.at = at;
        }
    }

    /**
     * This constructor handles the Event Task read from the hard drive.
     *
     * @param type Task type: Event.
     * @param isDone Whether the task has been done or not.
     * @param description Task description.
     * @param at Event timeline.
     */
    public Event(Type type, boolean isDone, String description, String at) {
        super(type, isDone, description);
        this.at = at;
    }

    /**
     * Returns the stored Event timeline.
     *
     * @return returns the stored Event timeline of the task.
     */
    @Override
    public String getTime() {
        return at;
    }

    /**
     * Returns the String representation of an Event.
     * as displayed in the list/mark done.
     *
     * @return returns the String representation of an Event.
     */
    @Override
    public String toString() {
        return super.toString() + " (at: " + at + ")";
    }
}
