/**
 * Class representing a Task with Deadline
 */
public class Deadline extends Task {

    protected String by;

    /**
     * Constructor of a Deadline, defaults to not completed.
     *
     * @param description Description of the Deadline.
     * @param by Due date of the Deadline
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Constructor of a Deadline, defaults to not completed.
     *
     * @param description Description of the Deadline.
     * @param isCompleted If the Deadline is completed.
     * @param by Due date of the Deadline
     */
    public Deadline(String description, Boolean isCompleted, String by) {
        super(description, isCompleted);
        this.by = by;
    }

    /**
     * Returns the due date of the Deadline.
     *
     * @return Due date of the deadline.
     */
    public String getBy() {
        return this.by;
    }

    /**
     * Returns String representation of the Deadline.
     *
     * @return String representation of the Deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    /**
     * Returns String representation of the Deadline in save format.
     *
     * @return String representation of the Deadline in save format.
     */
    @Override
    public String save() {
        return "D|" + (this.getStatus() ? "1" : "0") + "|" + this.getDescription() + "|" + this.getBy();
    }
}