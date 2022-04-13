package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that starts at a specific time and ends at a specific time.
 *
 * @author felix-ong
 */
public class Event extends Task {
    /** Date, start and end time of the event */
    protected LocalDateTime at;

    /**
     * Constructor of an Event Task.
     *
     * @param description Short description of task.
     */
    public Event(String description, LocalDateTime at) {
        super(description);
        this.at = at;
    }

    /**
     * Constructor of an Event Task.
     *
     * @param description Short description of task.
     */
    public Event(String description, LocalDateTime at, boolean isDone) {
        super(description);
        this.at = at;
        this.isDone = isDone;
    }

    /**
     * Returns a string representation of the task to be saved in.
     *
     * @return The string representation of the task.
     */
    @Override
    public String toSaveString() {
        return "E" + super.toSaveString() + String.format(",%s", this.at);
    }

    /**
     * Returns the string representation of an Event Task.
     *
     * @return The string representation of an Event Task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
                + this.at.format(DateTimeFormatter.ofPattern("dd MMM yyyy hh:mma")) + ")";
    }
}
