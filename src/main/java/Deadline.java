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
     * Constructor for the Deadline class.
     *
     * @param description The description of the task.
     * @param isDone A boolean indicating whether the task has been completed.
     * @param by The deadline of the task.
     */
    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone);
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

    @Override
    public String getSaveFormat() {
        return "D|" + super.getSaveFormat() + "|" + this.by + '\n';
    }
}
