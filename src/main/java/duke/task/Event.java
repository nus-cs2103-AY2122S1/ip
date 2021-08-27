package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event as a task in the list.
 */
public class Event extends Task {
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    /**
     * Constructor for event.
     *
     * @param description description of the event
     * @param startTime the time at which the event starts in datetime format
     * @param endTime the time at which the event ends in datetime format
     */
    public Event(String description, LocalDateTime startTime, LocalDateTime endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }


    /**
     * Convert the event into the specific string for data saving.
     *
     * @return the string representation in the specific format for database
     */
    @Override
    public String stringifyTask() {
        return String.format("E|%d|%s|%s|%s", this.isDone ? 1 : 0, this.description,
                this.startTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                this.endTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
    }


    /**
     * Return the string representation of the task.
     *
     * @return string representation of the task
     */
    @Override
    public String toString() {
        return String.format("[E]%s (from %s to %s)", super.toString(),
                this.startTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm")),
                this.endTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm")));
    }
}
