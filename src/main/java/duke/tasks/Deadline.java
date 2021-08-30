package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task.
 */
public class Deadline extends Task {

    private final LocalDateTime by;

    /**
     * Constructor for Deadline.
     *
     * @param description of the deadline.
     * @param by          when the deadline is.
     * @param isDone      if the task is done or not.
     */
    public Deadline(String description, LocalDateTime by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    public String getBy() {
        return by.toString();
    }

    public LocalDateTime getDate() {
        return by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy h:mm a"))
                + ")";
    }
}

