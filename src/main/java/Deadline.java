/**
 * A task that stores the deadline.
 */
public class Deadline extends Task {
    /**
     * The day of the deadline.
     */
    protected String by;

    /**
     * A constructor used to initialize the deadline.
     *
     * @param description the description of the deadline.
     * @param by the day of the deadline.
     */
    protected Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * A constructor used to initialize the deadline through file input.
     *
     * @param description the description of the event.
     * @param isCompleted the state of the event.
     * @param by the day of the deadline.
     */
    protected Deadline(String description, boolean isCompleted, String by) {
        super(description, isCompleted);
        this.by = by;
    }

    /**
     * Return the string representation of deadline.
     *
     * @return the string representation of deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }

    /**
     * Return the string representation of deadline for file input/output.
     *
     * @return the string representation of deadline for file input/output.
     */
    @Override
    public String fileFormat() {
        String displayCompletion = this.isCompleted ? "1" : "0";
        return String.format("%s | %s | %s | %s", "D", displayCompletion, this.description, this.by);
    }
}
