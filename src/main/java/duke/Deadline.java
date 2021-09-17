package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A class representing a deadline task.
 */
public class Deadline extends Task {
    private static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy");
    private String by;
    private LocalDate byDate;

    /**
     * A constructor for the deadline task.
     *
     * @param description The description of the task.
     * @param by The note represented by a string.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.byDate = null;
    }

    /**
     * A constructor for the deadline task which is mainly used for restoring from duke.txt.
     *
     * @param isDone The status of the task.
     * @param description The description of the task.
     * @param by A note represented by a string.
     * @param byDate The date of the deadline for the task.
     */
    public Deadline(boolean isDone, String description, String by, LocalDate byDate) {
        super(isDone, description);
        this.by = by;
        this.byDate = byDate;
    }

    /**
     * A constructor for the deadline task.
     *
     * @param description The description of the task.
     * @param byDate The date of the deadline for the task.
     */
    public Deadline(String description, LocalDate byDate) {
        super(description);
        this.by = null;
        this.byDate = byDate;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format(
                "[D]%s (by: %s)",
                super.toString(),
                byDate != null ? byDate.format(DATE_TIME_FORMAT) : by
        );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toBackupFormat() {
        return String.format(
                "D | %s | %s | %s | ",
                super.toBackupFormat(),
                by == null ? "" : by,
                byDate == null ? "" : byDate.format(DATE_TIME_FORMAT)
        );
    }
}
