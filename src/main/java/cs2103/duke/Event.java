package cs2103.duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * This class encapsulates an Event Task object, which inherits from the Task class.
 */
public class Event extends Task {

    protected String at;
    protected LocalDate date;

    public Event(String description, String at) {
        super(description);
        this.at = at;
        this.date = LocalDate.parse(at);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + date.format(DateTimeFormatter.ofPattern("yyyy MM dd")) + ")";
    }
}