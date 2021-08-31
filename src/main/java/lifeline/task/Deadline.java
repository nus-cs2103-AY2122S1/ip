package lifeline.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The Deadline class encapsulates a task with a deadline.
 */
public class Deadline extends Task {

    private LocalDateTime by;

    /**
     * Default constructor for a Deadline.
     *
     * @param name Name of the task with a deadline.
     * @param by Indicates when the task has to completed by.
     */
    public Deadline(String name, LocalDateTime by) {
        super(name);
        this.by = by;
    }

    /**
     * Constructor to be used when loading saved deadline.
     *
     * @param name Name of the task with a deadline.
     * @param by Indicates when the task has to completed by.
     * @param isDone Indicates if the task is completed.
     */
    public Deadline(String name, LocalDateTime by, boolean isDone) {
        super(name, isDone);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy h:mm a")) + ")";
    }

}
