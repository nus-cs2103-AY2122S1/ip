package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Task with a deadline.
 * The date of the deadline is stored as a LocalDate object.
 *
 * @author ruiquan
 */
public class Deadline extends Task {
    private LocalDate by;

    /**
     * Constructs a Deadline given the description and the deadline.
     * @param description the deadline description
     * @param by the deadline of this task
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Constructs a Deadline given the description,
     * completion status and deadline.
     * @param description the task description
     * @param isDone whether the task is completed
     * @param by the deadline of this task
     */
    public Deadline(String description, boolean isDone, LocalDate by) {
        super(description, isDone);
        this.by = by;
    }

    @Override
    public String format() {
        return String.format("D, %d, %s, %s", isDone ? 1 : 0, description, by);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)",
                super.toString(),
                by.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }
}
