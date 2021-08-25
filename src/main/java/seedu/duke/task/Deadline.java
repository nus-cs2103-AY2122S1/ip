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
     * @param deadline duke.task.Deadline to complete <code>Deadline</code>
     *                 task by.
     */
    public Deadline(String description, LocalDate deadline) {
        super(description);
        this.deadline = deadline;
    }

    /**
     * String representation of <code>Deadline</code> object.
     *
     * @return String representation of a <code>Deadline</code> object.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline +")";
    }

}
