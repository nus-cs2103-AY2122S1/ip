/**
 * The Deadline class encapsulates a deadline task.
 */
public class Deadline extends Task {
    /** The deadline for the task. */
    protected String by;

    /**
     * Constructor to initialise a Deadline task.
     * @param description The description of the Deadline task.
     * @param by The deadline for the Deadline task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the string representation of the Deadline task.
     * @return The string representation of the Deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
