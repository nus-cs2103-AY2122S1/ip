package seedu.duke.task;

import java.time.LocalDate;

/**
 * Class that encapsulates a duke.task.Deadline duke.task.
 */
public class Deadline extends Task {
    /**
     * String that indicates the deadline for the duke.task.
     */
    protected LocalDate deadline;

    /**
     * Public constructor to create a duke.task.Deadline duke.task
     *
     * @param description Description of the deadline duke.task.
     * @param deadline duke.task.Deadline to complete duke.task by.
     */
    public Deadline(String description, LocalDate deadline) {
        super(description);
        this.deadline = deadline;
    }

    /**
     * String representation of a deadline duke.task.
     *
     * @return String representation of a deadline duke.task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline +")";
    }

}
