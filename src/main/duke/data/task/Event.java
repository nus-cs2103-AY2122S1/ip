package duke.data.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime at;

    public Event(String description, LocalDateTime at) {
        super(description);
        this.at = at;
    }

    /**
     * Returns the data representation of an Event object.
     *
     * @return The formatted String.
     */
    @Override
    public String toData() {
        return "E|" + super.toData() + "|" + at.format(DateTimeFormatter.ofPattern("dd-MM-yyy HH:mm"));
    }

    /**
     * Returns the String representation of an Event object.
     *
     * @return THe String representation of an Event object.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + ")";
    }
}
