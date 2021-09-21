package duke.task;

import duke.util.Priority;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The class models an event which is a special type of task.
 */
public class Event extends Task {
    private final String ENCODE_STRING_FORMAT = "E %s %b %s /at %s %s";  // [type] [priority] [isDone] [description] /by [dateTime]
    private final String ENCODE_DATETIME_FORMAT = "yyyy/MM/dd HH:mm";

    private final String PRINT_STRING_FORMAT = "[E] [%s] [%s] %s (at: %s)";
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
     * Instantiates an Event object with given description and
     * event start dateTime and end dateTime and priority.
     *
     * @param description
     * @param startDateTime
     * @param endDateTime
     * @param priority
     */
    public Event(String description, LocalDateTime startDateTime, LocalDateTime endDateTime, Priority priority) {
        super(description, priority);
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }


    /**
     * Constructor.
     * Instantiates a Deadline object with given description, isDone status and
     * event start dateTime and end dateTime, and priority.
     *
     * @param description
     * @param isDone
     * @param startDateTime
     * @param endDateTime
     * @param priority
     */
    public Event(String description, boolean isDone,
                 LocalDateTime startDateTime, LocalDateTime endDateTime,
                 Priority priority) {
        super(description, isDone, priority);
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    /**
     * Encodes Event in the format of `taskType isDone description startDateTime endDateTime`
     *
     * @return encoded event string
     */
    public String encode() {
        return String.format(ENCODE_STRING_FORMAT,
                priority,
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
                priority,
                isDone ? PRINT_ISDONE_MARKER_POSITIVE : PRINT_ISDONE_MARKER_NEGATIVE,
                content,
                startDateTime
                        .format(DateTimeFormatter.ofPattern(PRINT_DATETIME_FORMAT))
                        + DATETIME_CONNECTOR
                        + endDateTime.format(
                        DateTimeFormatter.ofPattern(PRINT_DATETIME_FORMAT)));
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
                timeCompare = startDateTime.compareTo(o.dateTime);
            } else if (other.getClass().getName().equals("Event")) {
                Event o = (Event) other;
                timeCompare = startDateTime.compareTo(o.startDateTime);
            }

            if(timeCompare == 0) {
                return content.compareTo(other.content);
            }
            return timeCompare;
        }
        return priorityCompare;
    }
}

