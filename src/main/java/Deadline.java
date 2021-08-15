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
     * @param by the day of the deadline
     */
    protected Deadline(String description, String by) {
        super(description);
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
}
