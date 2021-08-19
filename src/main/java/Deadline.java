/**
 * A Task that needs to be done before a specific date/time.
 *
 * @author Lethicia
 */
public class Deadline extends Task{
    /** date/time by which task must be done. */
    protected String by;

    /**
     * Constructor for a Deadline task.
     *
     * @param description the title or description for the task
     * @param by date/time by which task must be done.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
