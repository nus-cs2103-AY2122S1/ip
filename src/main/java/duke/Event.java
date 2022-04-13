package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Event task consists of task description and event time.
 * Inherits from Task.
 */
public class Event extends Task {
    protected String at;

    /**
     * Create new Event
     * @param description Description of event
     * @param at Time of event
     * @param isDone If event is completed
     */
    public Event(String description, String at, boolean isDone) {
        super(description, isDone);
        String date;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            date = LocalDate.parse(at, formatter).format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        } catch (DateTimeParseException e) {
            date = at;
        }

        this.at = date;
    }

    public Event(String description, String at) {
        this(description, at, false);
    }


    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
