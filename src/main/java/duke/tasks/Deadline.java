package duke.tasks;

import java.time.LocalDateTime;

/**
 * Container for a deadline task.
 */
public class Deadline extends Task {
    /**
     * Instantiates a Deadline Object.
     *
     * @param description Description of the task.
     * @param by Due date of the task.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description, by);
    }

    /**
     * Instantiates a Deadline Object.
     *
     * @param description Description of the task.
     * @param isDone True if the task has been completed.
     * @param by Date which the task has to be completed by.
     */
    public Deadline(String description, boolean isDone, LocalDateTime by) {
        super(description, isDone, by);
    }

    /**
     * Returns the string representation of this Deadline Object.
     *
     * @return String representation of Deadline object.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + date.format(outputFormatter) + ")";
    }

    /**
     * Returns the string representation of the task to be saved in the hard disk.
     *
     * @return String to be saved in the hard disk.
     */
    @Override
    public String toSaveString() {
        return "D" + super.toSaveString() + "|" + date.format(outputFormatter);
    }
}
