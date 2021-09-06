package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Tasks which occur during a certain time
 */
public class Event extends Task {

    private LocalDateTime at;

    /**
     * Public constructor which creates a new Event object with a due date.
     *
     * @param taskName The description of the Event.
     * @param at The start time of the Event.
     */
    public Event(String taskName, LocalDateTime at) {
        super(taskName);
        this.at = at;
    }

    /**
     * Gets the start time of the Event.
     *
     * @return The string representation of the time of event.
     */
    public String getWhen() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm");
        return dtf.format(at);
    }

    /**
     * Gets the string representation of the Event object.
     *
     * @return The string representation of the Event object.
     */
    @Override
    public String displayInfo() {
        return String.format("[E] [%s] %s (at: %s)", this.getStatus(), this.getTaskName(), this.getWhen());
    }

    /**
     * Gets the string representation of the Event object,
     * used for writing into the local file system.
     *
     * @return The string representation of the event object,
     *          used for writing into the local file system.
     */
    @Override
    public String getSaveInfo() {
        if (this.isDone()) {
            return String.format("E | 1 | %s | %s | %s", this.getTaskName(), this.getWhen(), this.getTagsString());
        } else {
            return String.format("E | 0 | %s | %s | %s", this.getTaskName(), this.getWhen(), this.getTagsString());
        }
    }
}
