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
}
