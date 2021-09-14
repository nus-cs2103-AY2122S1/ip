package seedu.duke.task;

import java.time.LocalDate;

/**
 * Represents a Deadline task. A <code>Deadline</code> describes
 * the task and the deadline that the task needs to be completed by.
 */
public class Deadline extends Task {
    /**
     * String that indicates the deadline for the task.
     */
    protected LocalDate deadline;

    /**
     * Public constructor to create a <code>Deadline</code> object.
     *
     * @param description Description of the <code>Deadline</code>
     *                    task object.
     * @param deadline deadline to complete <code>Deadline</code>
     *                 task by.
     */
    public Deadline(String description, LocalDate deadline) {
        super(description);
        this.deadline = deadline;
    }

    /**
     * Public constructor to create a <code>Deadline</code> object.
     *
     * @param description Description of the <code>Deadline</code>
     *                    task object.
     * @param deadline deadline to complete <code>Deadline</code>
     *                 task by.
     * @param isCompleted Boolean indicating if task is completed.
     */
    public Deadline(String description, LocalDate deadline, boolean isCompleted) {
        super(description, isCompleted);
        this.deadline = deadline;
    }

    /**
     * Mark <code>Task</code> object as completed.
     */
    public Task markAsCompleted() {
        return new Deadline(super.description, this.deadline, true);
    }

    /**
     * String representation of <code>Deadline</code> object.
     *
     * @return String representation of a <code>Deadline</code> object.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline + ")";
    }

}
