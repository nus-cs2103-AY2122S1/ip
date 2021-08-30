package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event task.
 */
public class Event extends Task {

    private LocalDateTime at;

    /**
     * Constructor for Event task.
     *
     * @param description of the event.
     * @param at          when the event is.
     * @param isDone      if the task is done or not.
     */
    public Event(String description, LocalDateTime at, boolean isDone) {
        super(description, isDone);
        this.at = at;
    }

    public String getAt() {
        return at.toString();
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + at.format(DateTimeFormatter.ofPattern("MMM d yyyy h:mm a"))
                + ")";
    }
}
