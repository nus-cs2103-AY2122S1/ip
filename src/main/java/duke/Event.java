package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event task.
 */
public class Event extends Task {
    private static final String DATE_TIME_FORMAT = "dd MMM yyyy HH:mm";
    private static final String TASK_TYPE = "E";
    private final LocalDateTime time;

    /**
     * Class constructor specifying the task and the event date and time.
     *
     * @param task Event to attend.
     * @param time Date and time of event.
     * @param priority Priority level of the task
     */
    public Event(String task, LocalDateTime time, int priority) {
        super(task, TASK_TYPE, priority);
        this.time = time;
    }

    /**
     * Class constructor specifying the task and the event date and time
     *
     * @param task Event to attend.
     * @param completed Whether the event has been attended.
     * @param time Date and time of event.
     * @param priority Priority level of the task.
     */
    public Event(String task, boolean completed, LocalDateTime time, int priority) {
        super(task, completed, TASK_TYPE, priority);
        this.time = time;
    }

    /**
     * Returns the date and time of the event in a pre-defined format.
     *
     * @return Date and time of Event.
     */
    public String getTime() {
        return this.time.format(DateTimeFormatter.ofPattern(DATE_TIME_FORMAT));
    }

    @Override
    public String toString() {
        return String.format("[%s][%s][%s] %s (at: %s)", this.getTaskType(), this.getCompletedMarker(),
                this.getPriorityMarker(), this.getTask(), this.getTime());
    }

    @Override
    public String parseForStorage() {
        return String.format("%s | %d | %d | %s | %s", this.getTaskType(), this.getIsCompleted() ? 1 : 0,
                this.getPriority(), this.getTask(), this.getTime());
    }
}
