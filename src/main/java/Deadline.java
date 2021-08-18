/**
 * Represents a task that needs to be done before a specific date/time.
 *
 * @author felix-ong
 */
public class Deadline extends Task {
    /** Date/time of the deadline */
    protected String by;

    /**
     * Constructor of a Deadline Task.
     *
     * @param description
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the string representation of a Deadline Task.
     *
     * @return The string representation of a Deadline Task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }
}
