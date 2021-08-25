package duke;

import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

/**
 * This class encapsulates a deadline task in the Duke Application.
 */
public class Deadline extends Task {
    private LocalDate by;

    /**
     * Constructor for a new Deadline task.
     *
     * @param description The task description.
     * @param by The duedate of this task.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Constructor for a new Deadline task.
     *
     * @param description The task description.
     * @param by The duedate of this task.
     * @param isDone Whether this task is marked as done.
     */
    public Deadline(String description, LocalDate by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(),
                this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }

    @Override
    public String toSaveFormat() {
        return String.format("D,%s,%s", super.toSaveFormat(), this.by);
    }
}
