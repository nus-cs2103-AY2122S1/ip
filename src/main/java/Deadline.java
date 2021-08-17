public class Deadline extends Task {
    protected String by;

    /**
     * Constructor for Deadline
     * @param description the task description
     * @param by the date or time task needs to be done by
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns string representation of this deadline
     * @return string representation of this deadline
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }
}
