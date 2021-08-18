/**
 * Represents a task that needs to be done before a specific date/time.
 * E.g. submit assignment by 11/9/2021.
 */
public class Deadline extends Task {
    protected String by;

    /**
     * Class constructor.
     *
     * @param description Description of the Deadline.
     * @param by Time and Date of the Deadline.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the string representation of the Deadline.
     *
     * @return String representation of Deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
