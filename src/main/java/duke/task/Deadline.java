package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The class models a deadline which is a special type of task.
 */
public class Deadline extends Task {
    private final String ENCODE_STRING_FORMAT = "D %b %s /by %s";  // [type] [isDone] [description] /by [dateTime]
    private final String ENCODE_DATETIME_FORMAT = "yyyy/MM/dd HH:mm";

    private final String PRINT_STRING_FORMAT = "[D] [%s] %s (by: %s)";
    private final String PRINT_DATETIME_FORMAT = "MMM d yyyy, HH:mm";
    private final String PRINT_ISDONE_MARKER_POSITIVE = "X";
    private final String PRINT_ISDONE_MARKER_NEGATIVE = " ";

    protected LocalDateTime dateTime;

    /**
     * Constructor.
     * Instantiates a Deadline object with given description
     * deadline dateTime.
     *
     * @param description
     * @param dateTime
     */
    public Deadline(String description, LocalDateTime dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    /**
     * Constructor.
     * Instantiates a Deadline object with given description, idDone status and
     * deadline dateTime.
     *
     * @param description
     * @param dateTime
     */
    public Deadline(String description, boolean isDone, LocalDateTime dateTime) {
        super(description, isDone);
        this.dateTime = dateTime;
    }

    /**
     * Encode Deadline in format
     * "taskType isDone description dateTime"
     *
     * @return encoded event string
     */
    public String encode() {
        return String.format(ENCODE_STRING_FORMAT,
                isDone,
                content,
                dateTime.format(DateTimeFormatter.ofPattern(ENCODE_DATETIME_FORMAT))
        );
    }

    /**
     * String representation of Deadlines.
     *
     * @return String representation of the deadline.
     */
    public String toString() {
        return String.format(PRINT_STRING_FORMAT,
                isDone ? PRINT_ISDONE_MARKER_POSITIVE : PRINT_ISDONE_MARKER_NEGATIVE,
                content,
                dateTime.format(DateTimeFormatter.ofPattern(PRINT_DATETIME_FORMAT)));
    }
}
