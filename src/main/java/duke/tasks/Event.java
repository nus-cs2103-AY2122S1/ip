package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event task.
 */
public class Event extends Tasks {
    protected static final DateTimeFormatter FORMATTED = DateTimeFormatter.ofPattern("MMM d yyyy HHmm");
    protected LocalDateTime startTime;

    /**
     * Constructor for an Event task.
     * @param description description of the task
     * @param startTime when the task starts
     */
    public Event(String description, LocalDateTime startTime) {
        super(description.substring(6, description.indexOf("/at")));
        this.startTime = startTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (at: " + startTime.format(FORMATTED) + ")";
    }
}
