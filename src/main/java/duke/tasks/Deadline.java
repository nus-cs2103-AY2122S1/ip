package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline task.
 */
public class Deadline extends Tasks {
    protected static final DateTimeFormatter FORMATTED = DateTimeFormatter.ofPattern("MMM d yyyy HHmm");
    protected LocalDateTime dueTime;

    /**
     * Deadline task constructor
     * @param description description of the task
     * @param dueTime when the task is due
     */
    public Deadline(String description, LocalDateTime dueTime) {
        super(description.substring(9, description.indexOf("/by")));
        this.dueTime = dueTime;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString()
                + " (by: " + dueTime.format(FORMATTED) + ")";
    }
}
