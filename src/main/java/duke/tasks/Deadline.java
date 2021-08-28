package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task.
 */
public class Deadline extends Task {
    private LocalDateTime by;

    /**
     * Creates a new deadline task.
     *
     * @param description The task description.
     * @param isDone The status of the task.
     * @param by The deadline of the task.
     */
    public Deadline(String description, boolean isDone, LocalDateTime by) {
        super(description, isDone);
        this.by = by;
    }

    /**
     * Creates a new deadline task that is <em>not</em> done yet.
     *
     * @param description The task description.
     * @param by The deadline of the task.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toSaveFormat() {
        return "D" + super.toSaveFormat() + " | " + by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString()
                + " (by: " + by.format(DateTimeFormatter.ofPattern("dd MMM yyyy h.mma")) + ")";
    }
}
