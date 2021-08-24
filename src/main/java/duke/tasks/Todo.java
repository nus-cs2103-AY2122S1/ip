package duke.tasks;

/**
 * Container for a todo task.
 */
public class Todo extends Task {
    /**
     * Instantiates a Todo object.
     *
     * @param description description of the todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Instantiates a Todo object;
     *
     * @param description
     * @param isDone
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Returns the string representation of this Todo Object.
     *
     * @return string representation of Deadline object.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns the string representation of the task to be saved in the hard disk.
     *
     * @return string to be saved in the hard disk.
     */
    @Override
    public String toSaveString() {
        return "T" + super.toSaveString();
    }
}
