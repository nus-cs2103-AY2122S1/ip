package uhtredragnarson.task;

/**
 * The Deadline class extends the Task class and represents a deadline that has a specific
 * deadline timing.
 */
public class Deadline extends Task {

    protected String by;

    /**
     * Constructor for this class.
     *
     * @param title Description of the deadline.
     * @param by    Deadline timing
     */
    public Deadline(String title, String by) {
        super(title);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + "[" + this.getStatusIcon() + "] " + this.title + " (by: " + by + ")";
    }
}
