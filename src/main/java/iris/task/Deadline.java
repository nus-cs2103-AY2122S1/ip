package iris.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Deadline is a Task with a deadline.
 * Contains the due date of task.
 *
 * @author Cheong Yee Ming
 * @version Iris Level-10
 */
public class Deadline extends Task {

    protected final LocalDateTime by;

    /**
     * Constructor of a Deadline.
     *
     * @param description Description of task to be done.
     * @param isDone      Boolean representation of completion of task.
     * @param by          Deadline to complete the task.
     */
    public Deadline(String description, boolean isDone, LocalDateTime by) {
        super(description, isDone, TaskType.DEADLINE);
        assert !description.isBlank() : "Description of deadline cannot be blank.";
        this.by = by;
    }

    /**
     * Returns a string representation of deadline of the task
     * in the format dd-MM-yyyy HH:mm.
     *
     * @return String representation of deadline of task.
     */
    public String getDeadlineTime() {
        return "(by: " + by.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")) + ")";
    }

    /**
     * Returns a string representation of the status of task
     * containing the type of task as denoted by D,
     * completion status,
     * the task to be completed
     * and the deadline of the task.
     * @return String representation of status of task.
     */
    @Override
    public String getStatus() {
        return "[D]" + super.getStatus() + " " + getDeadlineTime();
    }

    /**
     * Returns string representation of deadline task.
     * Used when storing data in local directory.
     *
     * @return String representation of a Deadline.
     */
    @Override
    public String toString() {
        return "D" + " | " + super.toString() + " | " + by;
    }
}
