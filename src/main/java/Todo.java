/**
 * A subclass of Task.
 */
public class Todo extends Task {
    /**
     * Instantiates a todo task.
     * @param description description of the todo task.
     */
    public Todo(String description) {
        super(description);
    }

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

    @Override
    public String toSaveString() {
        return "T" + super.toSaveString();
    }
}
