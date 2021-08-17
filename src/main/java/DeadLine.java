/**
 * DeadLine class for implementing tasks that have a deadline.
 */
public class DeadLine extends Task {

    protected String by;

    /**
     * Constructor of the Deadline class.
     * @param description the name of the task
     * @param by when is the dateline of the task
     */
    public DeadLine(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by:" + by + ")";
    }
}
