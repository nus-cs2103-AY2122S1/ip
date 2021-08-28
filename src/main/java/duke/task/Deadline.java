package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a deadline task.
 */
public class Deadline extends Task {
    public static final String IDENTIFIER = "D";
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");
    private static final DateTimeFormatter DISPLAY_FORMAT = DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a");

    private LocalDateTime by;

    /**
     * Creates a deadline task.
     *
     * @param description Deadline's description.
     * @param isDone Status of task's completion.
     * @param by String representation of due date of deadline.
     * @throws DateTimeParseException If due date of deadline is not correctly formatted.
     */
    public Deadline(String description, boolean isDone, String by) throws DateTimeParseException {
        super(description, isDone);
        this.by = LocalDateTime.parse(by, INPUT_FORMAT);
    }

    /**
     * Creates a deadline task.
     *
     * @param description Deadline's description.
     * @param by String representation of due date of deadline.
     * @throws DateTimeParseException If due date of deadline is not correctly formatted.
     */
    public Deadline(String description, String by) throws DateTimeParseException{
        super(description);
        this.by = LocalDateTime.parse(by, INPUT_FORMAT);
    }

    @Override
    public String toString() {
        return "[" + IDENTIFIER + "]" + super.toString()
                + " (by:" + by.format(DISPLAY_FORMAT) + ")";
    }

    @Override
    public String toStorageFormat() {
        return IDENTIFIER + Task.STORAGE_DELIMITER + super.toStorageFormat() + Task.STORAGE_DELIMITER
                + by.format(INPUT_FORMAT);
    }
}
