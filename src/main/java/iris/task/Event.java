package iris.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Event is a Task with an occurring date.
 * Contains the time of task.
 *
 * @author Cheong Yee Ming
 * @version Iris Level-10
 */
public class Event extends Task {

    protected final LocalDateTime at;

    /**
     * Constructor of an Event.
     *
     * @param description Description of task to be done.
     * @param isDone      Boolean representation of completion of task.
     * @param at          Occurring time of the task.
     */
    public Event(String description, boolean isDone, LocalDateTime at) {
        super(description, isDone, TaskType.EVENT);
        assert !description.isBlank() : "Description of event cannot be blank.";
        this.at = at;
    }

    /**
     * Returns a string representation of occurring time of task.
     * in the format dd-MM-yyyy HH:mm.
     *
     * @return String representation of occurring time of task.
     */
    public String getEventTime() {
        return "(at: " + at.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")) + ")";
    }

    /**
     * Returns a string representation of the status of task
     * containing the type of task as denoted by E,
     * completion status,
     * the task to be completed
     * and the occurring time of the task.
     * @return String representation of status of task.
     */
    @Override
    public String getStatus() {
        return "[E]" + super.getStatus() + " " + getEventTime();
    }

    /**
     * Returns string representation of Event task.
     * Used when storing data in local directory.
     *
     * @return String representation of an Event.
     */
    @Override
    public String toString() {
        return "E" + " | " + super.toString() + " | " + at;
    }
}
