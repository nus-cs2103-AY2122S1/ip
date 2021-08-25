package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The class models an event which is a special type of task.
 */
public class Event extends Task {
    LocalDateTime startDateTime;
    LocalDateTime endDateTime;

    /**
     * Constructor.
     * Instantiates a Deadline object with given description and
     * event start dateTime and end dateTime.
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
     * @return encoded event string
     */
    public String encode() {
        return String.format("E %b %s /at %s %s",
                isDone,
                content,
                startDateTime
                        .format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm")),
                endDateTime
                        .format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"))
                );
    }

    /**
     * String representation of Events.
     * @return String representation of the event.
     */
    public String toString() {
        return String.format("[E] [%s] %s (at: %s)",
                isDone ? "X" : " ",
                content,
                startDateTime
                        .format(DateTimeFormatter.ofPattern("MMM d yyyy, HH:mm"))
                        + " - "
                        + endDateTime.format(
                                DateTimeFormatter.ofPattern("MMM d yyyy, HH:mm")));
    }
}

