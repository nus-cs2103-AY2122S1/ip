package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Event is a class that extends Task with additional variables to store the date.
 *
 * @author meerian
 */
public class Event extends Task {
    /**
     * Represents the date when the event occurs. Only one field is 'filled' in each
     * event object.
     */
    private String when;
    private LocalDate time = null;

    /**
     * Creates an event with the specified description.
     *
     * @param str1 The event's description.
     * @param str2 The event's date in a string
     * @throws DukeException when the user inputs an empty description.
     */
    public Event(String str1, String str2) throws DukeException {
        super(str1);
        this.when = str2;
    }

    /**
     * Creates an event with the specified description.
     *
     * @param str1 The event's description.
     * @param time The event's date in a localDate
     * @throws DukeException when the user inputs an empty description.
     */
    public Event(String str1, LocalDate time) throws DukeException {
        super(str1);
        this.time = time;
    }

    /**
     * Returns the string representation of the event to be displayed based on whether
     * the object is initialized with a localDate or String.
     *
     * @return the string representation of the event.
     */
    @Override
    public String toString() {
        if (time == null) {
            return "[E]" + super.toString() + " (at: " + when + ")";
        } else {
            return "[E]" + super.toString() + " (at: "
                + time.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
        }
    }
}
