package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event task.
 */
public class Event extends Task {

    private final LocalDateTime at;

    /**
     * Constructor for Event task.
     *
     * @param description Description of the event.
     * @param at          When the event is.
     * @param isDone      If the task is done or not.
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
        return "[E]" + super.toString() + "(at: " + at.format(DateTimeFormatter.ofPattern("MMM d yyyy h:mm a")) + ")";
    }
}
