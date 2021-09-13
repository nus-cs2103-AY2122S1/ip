package duke.task;

/**
 * Represents a task to be completed with unspecified time.
 */
public class ToDo extends Task {
    /**
     * Constructor of the class `ToDo`.
     *
     * @param description Description of the task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Converts the task to string.
     *
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Converts the task to a string with the format of the file in hard disk.
     *
     * @return String representation of the task in the file's format.
     */
    @Override
    public String toFileFormatString() {
        return String.format("T / %s / %s\n", this.isDone ? "1" : "0", this.description);
    }
}
