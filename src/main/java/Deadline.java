/**
 * A subclass of Task to represent a task with deadline.
 */
public class Deadline extends Task {
    /**
     * Due date of the task.
     */
    private String by;

    /**
     * Constructor to instantiate a Deadline Object.
     *
     * @param description description of the task.
     * @param by due date of the task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * A method that returns the string representation of this Deadline Object.
     *
     * @return String representation of Deadline object.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }
}
