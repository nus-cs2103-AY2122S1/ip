/**
 * Represents a task with a deadline. A subclass of the Task class.
 */
public class Deadline extends Task {
    /** Deadline of this task as a string */
    protected String by;

    /**
     * Constructor of the class `Deadline`.
     *
     * @param description Description of the task.
     * @param by Deadline of the task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Converts a task with deadline to a string.
     *
     * @return The string representation of a task with deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }
}
