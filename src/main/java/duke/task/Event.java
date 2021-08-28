package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an event task.
 */
public class Event extends Task {
    public static final String IDENTIFIER = "E";
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");
    private static final DateTimeFormatter DISPLAY_FORMAT = DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a");

    private LocalDateTime at;

    /**
     * Creates a event task.
     *
     * @param description Events's description.
     * @param isDone Status of task's completion.
     * @param at String representation of date of event.
     * @throws DateTimeParseException If date of event is not correctly formatted.
     */
    public Event(String description, boolean isDone, String at) throws DateTimeParseException {
        super(description, isDone);
        this.at = LocalDateTime.parse(at, INPUT_FORMAT);
    }

    /**
     * Creates an event task.
     *
     * @param description Events's description.
     * @param at String representation of date of event.
     * @throws DateTimeParseException If date of event is not correctly formatted.
     */
    public Event(String description, String at) throws DateTimeParseException {
        super(description);
        this.at = LocalDateTime.parse(at, INPUT_FORMAT);
    }

    @Override
    public String toString() {
        return "[" + IDENTIFIER + "]" + super.toString()
                + " (at:" + at.format(DISPLAY_FORMAT)  + ")";
    }
    @Override
    public String toStorageFormat() {
        return IDENTIFIER + Task.STORAGE_DELIMITER + super.toStorageFormat() + Task.STORAGE_DELIMITER
                + at.format(INPUT_FORMAT);
    }
}
