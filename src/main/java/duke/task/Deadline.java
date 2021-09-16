package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * This class encapsulates a deadline task in the Duke Application.
 */
public class Deadline extends Task {
    private LocalDate dueBy;

    /**
     * Constructor for a new Deadline task.
     *
     * @param description The task description.
     * @param by          The duedate of this task.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.dueBy = by;
    }

    /**
     * Constructor for a new Deadline task.
     *
     * @param description The task description.
     * @param by          The duedate of this task.
     * @param isDone      Whether this task is marked as done.
     */
    public Deadline(String description, LocalDate by, boolean isDone) {
        super(description, isDone);
        this.dueBy = by;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(),
                this.dueBy.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }

    @Override
    public String toSaveFormat() {
        return String.format("D,%s,%s", super.toSaveFormat(), this.dueBy);
    }
}
