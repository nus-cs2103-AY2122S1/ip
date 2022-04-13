package duke.tasks;

/**
 * Container for a todo task.
 */
public class Todo extends Task {
    /**
     * Instantiates a Todo object.
     *
     * @param description Description of the todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Instantiates a Todo object.
     *
     * @param description Description of the todo task.
     * @param isDone True if the task has been completed, false otherwise.
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Returns the string representation of this Todo Object.
     *
     * @return String representation of Deadline object.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns the string representation of the task to be saved in the hard disk.
     *
     * @return String to be saved in the hard disk.
     */
    @Override
    public String toSaveString() {
        return "T" + super.toSaveString();
    }
}
