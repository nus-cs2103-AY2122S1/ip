/**
 * Encapsulates a task to be done with a deadline.
 */
public class Deadlines extends Task {
    /** When the task must be done by. */
    private String limit;

    /**
     * Constructor of a Deadlines object.
     *
     * @param description Description of the task.
     * @param limit When the task must be done by.
     */
    public Deadlines(String description, String limit) {
        super(description);
        this.limit = limit;
    }

    /**
     * Returns the string form of the Deadlines object.
     *
     * @return The string form of the Deadlines.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.limit + ")";
    }
}
