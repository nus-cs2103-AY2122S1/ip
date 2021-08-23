public class Deadline extends TaskItem {

    protected String by = null;

    /**
     * Constructor for creating a Deadline object.
     * @param description description of the task.
     * @param by the by-date of the task, e.g. "by Sunday"
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description) {
        super(description);
    }

    /**
     * Overriden toString() method.
     * @return a string representation of the Deadline object.
     */
    @Override
    public String toString() {
        if (this.by != null) {
            return "[D]" + super.toString() + "(by: " + this.by + ")";
        } else {
            return "[D]" + super.toString();
        }
    }
}
