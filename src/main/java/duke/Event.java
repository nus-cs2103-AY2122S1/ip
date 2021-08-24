package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * 
 * A subclass of the Task Object. 
 * Includes the date of the event.
 * 
 */
public class Event extends Task {

    protected LocalDate date;

    public Event(String description, String at) {
        super(description);
        this.date = LocalDate.parse(at);
    }

    public Event(String description, boolean isDone, String at) {
        super(description, isDone);
        this.date = LocalDate.parse(at);
    }

    @Override
    public String toString() {
        return "E " + super.toString() + " | " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));

    }
}
