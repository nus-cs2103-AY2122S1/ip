/**
 * Represents a task with a deadline. A subclass of the Task class.
 */
public class Deadline extends Task {
    /** Deadline of this task as a string */
    protected String by;

    /**
     * Constructor of the class `Deadline`.
     *
     * @param description Description of the task.
     * @param by Deadline of the task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Converts a task with deadline to a string.
     *
     * @return The string representation of a task with deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }

    /**
     * Converts the task to a string with the format of the file in hard disk.
     *
     * @return String representation of the task in the file's format.
     */
    @Override
    public String toFileFormatString() {
        return String.format("D / %s / %s / %s\n", this.isDone ? "1" : "0", this.description, this.by);
    }
}
