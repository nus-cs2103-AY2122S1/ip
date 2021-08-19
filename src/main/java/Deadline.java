/**
 * Task that need to be done before a specific date/time
 */
public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * String representation of this task.
     *
     * @return The string representation.
     */
    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + by + ")";
    }
}