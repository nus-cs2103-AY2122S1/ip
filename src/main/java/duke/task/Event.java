package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This class represents an Event, which is a Task with a timing.
 */
public class Event extends Task {
    private LocalDateTime time;

    /**
     * Constructor for Event, which takes in a task name and a time for the event.
     *
     * @param taskName name of task.
     * @param time time of event.
     */
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
    public String getType() {
        return "E";
    }

    /**
     * Gives save-friendly information.
     *
     * @return save-friendly information.
     */
    @Override
    public String getSaveInfo() {
        return super.getSaveInfo() + "| "
                + this.time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    /**
     * Overriden toString method.
     *
     * @return string representation of the task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString(this.time, "at ");
    }
}
