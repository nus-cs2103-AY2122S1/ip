package duke.task;

import duke.util.Priority;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The class models a deadline which is a special type of task.
 */
public class Deadline extends Task {
    private final String ENCODE_STRING_FORMAT = "D %s %b %s /by %s";  // [type] [priority] [isDone] [description] /by [dateTime]
    private final String ENCODE_DATETIME_FORMAT = "yyyy/MM/dd HH:mm";

    private final String PRINT_STRING_FORMAT = "[D] [%s] [%s] %s (by: %s)";
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
     * @param isDone
     * @param dateTime
     */
    public Deadline(String description, boolean isDone, LocalDateTime dateTime) {
        super(description, isDone);
        this.dateTime = dateTime;
    }


    /**
     * Constructor.
     * Instantiates a Deadline object with given description,
     * deadline dateTime, and deadline priority.
     *
     * @param description
     * @param dateTime
     * @param priority
     */
    public Deadline(String description, LocalDateTime dateTime, Priority priority) {
        super(description, priority);
        this.dateTime = dateTime;
    }


    /**
     * Constructor.
     * Instantiates a Deadline object with given description, idDone status,
     * deadline dateTime, and deadline priority.
     *
     * @param description
     * @param isDone
     * @param dateTime
     * @param priority
     */
    public Deadline(String description, boolean isDone, LocalDateTime dateTime, Priority priority) {
        super(description, isDone, priority);
        this.dateTime = dateTime;
    }

    /**
     * Encodes Deadline in the format of `taskType isDone description dateTime`
     *
     * @return encoded event string
     */
    public String encode() {
        return String.format(ENCODE_STRING_FORMAT,
                priority,
                isDone,
                content,
                dateTime.format(DateTimeFormatter.ofPattern(ENCODE_DATETIME_FORMAT))
        );
    }

    /**
     * Returns the string representation of Deadlines.
     *
     * @return String representation of the deadline.
     */
    public String toString() {
        return String.format(PRINT_STRING_FORMAT,
                priority,
                isDone ? PRINT_ISDONE_MARKER_POSITIVE : PRINT_ISDONE_MARKER_NEGATIVE,
                content,
                dateTime.format(DateTimeFormatter.ofPattern(PRINT_DATETIME_FORMAT)));
    }


    /**
     * Compares two deadlines' priority.
     * If there is a tie of priority, then compare by dateTime that
     * the one with nearer dateTime has higher priority.
     *
     * @param other
     * @return
     */
    public int compareTo(Task other) {
        if(other.getClass().getName().equals("Task")) {
            return -1;
        }

        int priorityCompare = priority.compareTo(other.priority);

        if (priorityCompare == 0) {
            int timeCompare = 0;
            if(other.getClass().getName().equals("Deadline")) {
                Deadline o = (Deadline) other;
                timeCompare = dateTime.compareTo(o.dateTime);
            } else if (other.getClass().getName().equals("Event")) {
                Event o = (Event) other;
                timeCompare = dateTime.compareTo(o.startDateTime);
            }

            if(timeCompare == 0) {
                return content.compareTo(other.content);
            }
            return timeCompare;
        }
        return priorityCompare;
    }
}
