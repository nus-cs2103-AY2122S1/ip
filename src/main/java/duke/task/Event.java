package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This class represents an Event, which is a Task with a timing.
 */
public class Event extends Task {
    LocalDateTime time;

    public Event(String taskName, LocalDateTime time) {
        super(taskName);
        this.time = time;
    }

    /**
     * Gives the type of the task.
     *
     * @return E for event.
     */
    @Override
    public String type() {
        return "E";
    }

    /**
     * Gives save-friendly information.
     *
     * @return save-friendly information.
     */
    @Override
    public String getSaveInfo() {
        return super.getSaveInfo() + "| " + this.time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + this.time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) + ")";
    }
}
