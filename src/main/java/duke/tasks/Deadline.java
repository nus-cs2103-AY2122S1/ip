package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline task.
 */
public class Deadline extends Tasks {
    protected static final DateTimeFormatter formatted = DateTimeFormatter.ofPattern("MMM d yyyy HHmm");
    protected LocalDateTime dueTime;

    public Deadline(String description, LocalDateTime dueTime) {
        super(description.substring(9, description.indexOf("/by")));
        this.dueTime = dueTime;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString()
                + " (by: " + dueTime.format(formatted) + ")";
    }
}