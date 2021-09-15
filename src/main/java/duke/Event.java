package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event task.
 */
public class Event extends Task {
    private static final String DATE_TIME_FORMAT = "dd MMM yyyy HH:mm";
    private static final String TASK_TYPE = "E";
    private LocalDateTime time;

    /**
     * Class constructor specifying the task and the event date and time.
     * @param task Event to attend.
     * @param time Date and time of event.
     */
    public Event(String task, LocalDateTime time) {
        super(task, TASK_TYPE);
        this.time = time;
    }

    /**
     * Class constructor specifying the task and the event date and time
     * @param task Event to attend.
     * @param completed Whether the event has been attended.
     * @param time Date and time of event.
     */
    public Event(String task, boolean completed, LocalDateTime time) {
        super(task, completed, TASK_TYPE);
        this.time = time;
    }

    /**
     * Returns the date and time of the event in a pre-defined format.
     * @return Date and time of Event.
     */
    public String getTime() {
        return this.time.format(DateTimeFormatter.ofPattern(DATE_TIME_FORMAT));
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s (at: %s)", this.getTaskType(), this.getCompletedMarker(),
                this.getTask(), this.getTime());
    }

    @Override
    public String parseForStorage() {
        return String.format("%s | %d | %s | %s", this.getTaskType(), this.getIsCompleted() ? 1 : 0,
                this.getTask(), this.getTime());
    }
}
