/**
 * The Deadline class encapsulates a task that has a deadline.
 */
public class Deadline extends Task {
    /** The deadline of the task. */
    private String by;

    /**
     * Constructor for the Deadline class.
     *
     * @param description The description of the task.
     * @param by The deadline of the task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the string representation of the task.
     *
     * @return A string representing the Deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }
}
