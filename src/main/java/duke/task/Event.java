package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The class models an event which is a special type of task.
 */
public class Event extends Task {
    private final String ENCODE_STRING_FORMAT = "E %b %s /at %s %s";  // [type] [isDone] [description] /by [dateTime]
    private final String ENCODE_DATETIME_FORMAT = "yyyy/MM/dd HH:mm";

    private final String PRINT_STRING_FORMAT = "[E] [%s] %s (at: %s)";
    private final String PRINT_DATETIME_FORMAT = "MMM d yyyy, HH:mm";
    private final String PRINT_ISDONE_MARKER_POSITIVE = "X";
    private final String PRINT_ISDONE_MARKER_NEGATIVE = " ";
    private final String DATETIME_CONNECTOR = " - ";

    protected LocalDateTime startDateTime;
    protected LocalDateTime endDateTime;

    /**
     * Constructor.
     * Instantiates an Event object with given description and
     * event start dateTime and end dateTime.
     *
     * @param description
     * @param startDateTime
     * @param endDateTime
     */
    public Event(String description, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        super(description);
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    /**
     * Constructor.
     * Instantiates a Deadline object with given description, isDone status and
     * event start dateTime and end dateTime.
     *
     * @param description
     * @param isDone
     * @param startDateTime
     * @param endDateTime
     */
    public Event(String description, boolean isDone, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        super(description, isDone);
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    /**
     * Encode Event in format
     * "taskType isDone description startDateTime endDateTime"
     *
     * @return encoded event string
     */
    public String encode() {
        return String.format(ENCODE_STRING_FORMAT,
                isDone,
                content,
                startDateTime
                        .format(DateTimeFormatter.ofPattern(ENCODE_DATETIME_FORMAT)),
                endDateTime
                        .format(DateTimeFormatter.ofPattern(ENCODE_DATETIME_FORMAT))
        );
    }

    /**
     * String representation of Events.
     *
     * @return String representation of the event.
     */
    public String toString() {
        return String.format(PRINT_STRING_FORMAT,
                isDone ? PRINT_ISDONE_MARKER_POSITIVE : PRINT_ISDONE_MARKER_NEGATIVE,
                content,
                startDateTime
                        .format(DateTimeFormatter.ofPattern(PRINT_DATETIME_FORMAT))
                        + DATETIME_CONNECTOR
                        + endDateTime.format(
                        DateTimeFormatter.ofPattern(PRINT_DATETIME_FORMAT)));
    }
}

