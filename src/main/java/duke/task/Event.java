package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an event task.
 */
public class Event extends Task {
    public static final String IDENTIFIER = "E";

    private LocalDateTime eventDate;

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
        this.eventDate = LocalDateTime.parse(at, DATE_INPUT_FORMAT);
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
        this.eventDate = LocalDateTime.parse(at, DATE_INPUT_FORMAT);
    }

    @Override
    public String toString() {
        return "[" + IDENTIFIER + "]" + super.toString()
                + " (at:" + eventDate.format(DATE_DISPLAY_FORMAT)  + ")";
    }
    @Override
    public String toStorageFormat() {
        return IDENTIFIER + Task.STORAGE_DELIMITER + super.toStorageFormat()
                + Task.STORAGE_DELIMITER + eventDate.format(DATE_INPUT_FORMAT);
    }
}
