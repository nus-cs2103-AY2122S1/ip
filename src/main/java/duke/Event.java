package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an event that consists of a task to be done on a specified date and time.
 */
public class Event extends Task {

    protected LocalDateTime dateTime;

    /**
     * Class constructor that constructs a Event object.
     *
     * @param description Description of the event task.
     * @param dateTime Date and time for the task to be done at.
     */
    public Event(String description, String dateTime) throws DukeException {
        super(description);

        try {
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
            this.dateTime = LocalDateTime.parse(dateTime, dateFormat);
        } catch (DateTimeParseException e) {
            throw new DukeException("you have to input the date and time like this! (eg. 11/03/1999 2000)");
        }
    }

    /**
     * Returns a String representation of Event.
     *
     * @return String representation of Event.
     */
    @Override
    public String toString() {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd MMM yyyy hh:mma");
        return "[E]" + super.toString() + " (at: " + this.dateTime.format(dateFormat) + ")";
    }
}
