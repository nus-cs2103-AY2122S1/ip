package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with the time it happens
 */
public class EventTask extends Task {
    private LocalDateTime date;

    /**
     * Constructor for <code>EventTask</code>
     *
     * @param content content of the task
     * @param isDone true if the task is done, otherwise false
     * @param date the time it happens
     */
    public EventTask(String content, boolean isDone, LocalDateTime date) {
        super(content, isDone);
        this.date = date;
    }

    /**
     * Getter for the happening time of the event
     *
     * @return the time of the event
     */
    public LocalDateTime getDate() {
        return date;
    }

    /**
     * Returns the string representation of the object
     *
     * @return string representation of the object
     */
    @Override
    public String toString() {
        return String.format(
                "[E]%s (at: %s)",
                super.toString(),
                this.date.format(DateTimeFormatter.ofPattern("MMM dd, yyyy HH:mm")));
    }
}
