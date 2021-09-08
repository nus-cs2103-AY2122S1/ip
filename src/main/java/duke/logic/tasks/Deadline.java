package duke.logic.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Represents a deadline task.
 */
public class Deadline extends Task {
    private static final String DEADLINE_DATE_FORMAT = "dd MMM yyyy h.mma";
    private static final Locale REGION = Locale.UK;

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
    public String getSaveFormat() {
        return "D" + super.getSaveFormat() + " | " + by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString()
                + " (by: " + by.format(DateTimeFormatter.ofPattern(DEADLINE_DATE_FORMAT, REGION)) + ")";
    }
}
