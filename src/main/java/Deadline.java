public class Deadline extends Task {

    /**
     * String representation of due date of Deadline.
     */
    protected String by;

    /**
     * Constructor for Deadline object.
     * @param description Description of Deadline.
     * @param by Due date of Deadline.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.type = TaskType.DEADLINE;
    }

    /**
     * Returns string representation of Deadline object.
     * @return String representation of Deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + by + ")";
    }
}