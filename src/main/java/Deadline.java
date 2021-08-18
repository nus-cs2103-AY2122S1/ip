public class Deadline extends TaskItem {

    protected String by;

    /**
     * Constructor for creating a Deadline object.
     * @param description description of the task.
     * @param by the by-date of the task, e.g. "by Sunday"
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Overriden toString() method.
     * @return a string representation of the Deadline object.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + this.by + ")";
    }
}
